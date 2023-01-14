package com.vehicule.vehicule.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicule.vehicule.DAO.RequeteNonEnvoyeDAO;
import com.vehicule.vehicule.Model.RequeteNonEnvoye;

@Repository
public class RequeteNonEnvoyeRepository implements RequeteNonEnvoyeDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void insertRequeteNonEnvoye(RequeteNonEnvoye requete) {
        String sql="INSERT INTO RequeteNonEnvoye(id,request1,request2) values (nextval('requetenonenvoyesequence'),'"+requete.getRequest1()+"','"+requete.getRequest2()+"')";
        jdbcTemplate.update(sql);
    }
    
}
