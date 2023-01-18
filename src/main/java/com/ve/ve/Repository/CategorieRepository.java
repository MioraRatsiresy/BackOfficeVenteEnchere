package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.CategorieDAO;
import com.ve.ve.Model.Categorie;
import com.ve.ve.Model.Commission;

@Repository
public class CategorieRepository implements CategorieDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public ArrayList<Categorie> getCategorie() {
        String sql = "select * from categorie";
        return (ArrayList<Categorie>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Categorie>(Categorie.class));
    }

    @Override
    public void insertCategorie(Categorie categorie) {
        String sql="INSERT INTO categorie values (default,'"+categorie.getCategorie()+"')";
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateCommission(Commission com) {
        String sql="Update commission set pourcentage="+com.getPourcentage();
        jdbcTemplate.update(sql);        
    }

    @Override
    public ArrayList<Commission> getCommission() {
        String sql = "select * from commission";
        return (ArrayList<Commission>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Commission>(Commission.class));

    }
    
}
