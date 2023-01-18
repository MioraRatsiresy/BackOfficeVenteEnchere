package com.ve.ve.Repository;

import java.util.ArrayList;

import com.ve.ve.DAO.StatistiqueChiffreAffaireDAO;
import com.ve.ve.Model.StatistiqueChiffreAffaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class StatistiqueChiffreAffaireRepository implements StatistiqueChiffreAffaireDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<StatistiqueChiffreAffaire> getAll() {
        String sql = "select * from v_chiffreaffaire";
        return (ArrayList<StatistiqueChiffreAffaire>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<StatistiqueChiffreAffaire>(StatistiqueChiffreAffaire.class));

    }

}
