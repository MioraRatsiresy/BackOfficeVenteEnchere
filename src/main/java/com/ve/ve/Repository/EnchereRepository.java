package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.EnchereDAO;
import com.ve.ve.Model.Enchere;

@Repository
public class EnchereRepository implements EnchereDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Enchere> getListeEnchere() {
        String sql = "select * from enchere";
        return (ArrayList<Enchere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Enchere>(Enchere.class));

    }

    @Override
    public ArrayList<Enchere> searchEnchere(String search) {
        String sql = "SELECT * from EnchereDetail where dateheure like '%"+search+"%' or lower(cartegorie) like '%"+search+"%' or lower(prixMin::varchar) like '%"+search+"%' or lower(etat) like '%"+search+"%'";
        return  (ArrayList<Enchere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Enchere>(Enchere.class));
    }

    @Override
    public ArrayList<Enchere> getMesEncheres(int id) {
        String sql = "select * from enchere where idclient="+id;
        return (ArrayList<Enchere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Enchere>(Enchere.class));
    }

    @Override
    public void insertEnchere(Enchere enchere) {
        String sql="INSERT INTO enchere values (default,'"+enchere.getProduit()+"','"+enchere.getLibelle()+"','"+enchere.getPrixMin()+"','"+enchere.getDuree()+"','"+enchere.getEtat()+"','"+enchere.getIdclient()+"')";
        jdbcTemplate.update(sql);
        
    }
}
