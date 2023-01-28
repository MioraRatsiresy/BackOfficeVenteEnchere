package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.EnchereDAO;
import com.ve.ve.DAO.MesEncheresDAO;
import com.ve.ve.Model.Enchere;
import com.ve.ve.Model.MesEncheres;

@Repository
public class MesEncheresRepository implements MesEncheresDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Enchere> getListeEnchere() {
        String sql = "select * from getInfoEnchere()";
        return (ArrayList<Enchere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Enchere>(Enchere.class));

    }

    @Override
    public ArrayList<MesEncheres> getMesEncheres(int id) {
        String sql = "select * from getInfoEnchere() where idclient="+id;
        System.out.println("Requete : "+sql);
        return (ArrayList<MesEncheres>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<MesEncheres>(MesEncheres.class));
    }

}