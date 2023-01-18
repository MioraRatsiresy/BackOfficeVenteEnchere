package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.EnchereDAO;
import com.ve.ve.DAO.StatistiqueCategorieDAO;
import com.ve.ve.Model.Enchere;
import com.ve.ve.Model.StatistiqueCategorie;

@Repository
public class StatistiqueCategorieRepository implements StatistiqueCategorieDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<StatistiqueCategorie> getAll() {
        String sql = "select * from  v_statistiqueCategorie";
        return (ArrayList<StatistiqueCategorie>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<StatistiqueCategorie>(StatistiqueCategorie.class));

    }

}
