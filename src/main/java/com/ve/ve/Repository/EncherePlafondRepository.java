package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.EncherePlafondDAO;
import com.ve.ve.Model.EncherePlafond;

@Repository
public class EncherePlafondRepository implements EncherePlafondDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public ArrayList<EncherePlafond> getEncherePlafond(int idclient,int enchere) {
        String sql = "select * from enchereplafond where idclient="+idclient+" and idenchere="+enchere;
        return (ArrayList<EncherePlafond>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<EncherePlafond>(EncherePlafond.class));

    }

    @Override
    public void insertPlafond(EncherePlafond plafond) {
        String sql="INSERT INTO enchereplafond values ('"+plafond.getIdClient()+"','"+plafond.getIdEnchere()+"',"+plafond.getMontant()+","+plafond.getIntervalle()+")";
        jdbcTemplate.update(sql);
        
    }
    
}
