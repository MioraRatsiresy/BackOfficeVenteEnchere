package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.EnchereDAO;
import com.ve.ve.Model.Enchere;
import com.ve.ve.Model.MesEncheres;
import com.ve.ve.Model.MiserEnchere;

@Repository
public class EnchereRepository implements EnchereDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Enchere> getListeEnchere() {
        String sql = "select * from getInfoEnchere()";
        return (ArrayList<Enchere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Enchere>(Enchere.class));

    }

    @Override
    public ArrayList<MesEncheres> searchEnchere(String search) {
        String sql = "SELECT * from getInfoEnchere() where (dateheure::varchar) like '%"+search+"%' or lower(categorie) like '%"+search+"%' or lower(prixMin::varchar) like '%"+search+"%' or lower(statut) like '%"+search+"%'";
        System.out.println(sql);
        return  (ArrayList<MesEncheres>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<MesEncheres>(MesEncheres.class));
    }

    @Override
    public ArrayList<Enchere> getMesEncheres(int id) {
        String sql = "select * from enchere where idclient="+id;
        return (ArrayList<Enchere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Enchere>(Enchere.class));
    }

    @Override
    public void insertEnchere(Enchere enchere) {
        String sql="INSERT INTO enchere values (default,'"+enchere.getProduit()+"','"+enchere.getLibelle()+"',current_timestamp,'"+enchere.getPrixMin()+"','"+enchere.getDuree()+"','"+enchere.getEtat()+"','"+enchere.getIdclient()+"')";
        System.out.println(sql);
        jdbcTemplate.update(sql);
        
    }

    public Object maxmontant(MiserEnchere miser){
        String sql = "select montantmax("+miser.getIdEnchere()+","+miser.getMontant()+","+miser.getIdclient()+")";
        return (Object) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Object>(Object.class));
    }


    public void rencherir(MiserEnchere miser) {
        String sql="INSERT INTO miserenchere(idclient,idenchere,montant) values ("+miser.getIdclient()+","+miser.getIdEnchere()+","+miser.getMontant()+")";
        jdbcTemplate.update(sql);
        this.maxmontant(miser);
        
    }
}
