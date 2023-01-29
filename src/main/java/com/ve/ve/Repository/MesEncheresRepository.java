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
    public ArrayList<MesEncheres> getListeEnchere() {
        String sql = "select * from getInfoEnchere()";
        System.out.println(sql);
        return (ArrayList<MesEncheres>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<MesEncheres>(MesEncheres.class));

    }

    @Override
    public ArrayList<MesEncheres> getMesEncheres(int id) {
        String sql = "select * from getInfoEnchere() where idclient="+id;
        System.out.println("Requete : "+sql);
        return (ArrayList<MesEncheres>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<MesEncheres>(MesEncheres.class));
    }

    public ArrayList<MesEncheres> getFicheEnchere(int id) {
        String sql = "select * from getInfoEnchere() where idenchere="+id;
        return (ArrayList<MesEncheres>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<MesEncheres>(MesEncheres.class));
    }

}
