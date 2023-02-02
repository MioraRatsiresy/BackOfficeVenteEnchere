package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.ClientDAO;
import com.ve.ve.DAO.CompteClientDAO;
import com.ve.ve.Model.Client;
import com.ve.ve.Model.CompteClient;
import com.ve.ve.Model.SoldeClient;

@Repository
public class CompteClientRepository implements CompteClientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<SoldeClient> getSolde(int idClient) {
        //String sql = "select clientid, sum(montant) as solde from compteclient where etat=0 and clientid=1 and actiontransaction=4 or etat=3 and clientid=1 and actiontransaction=4 group by clientid";
        String sql = "select id, solde from SoldeClient where id="+idClient;
        return (ArrayList<SoldeClient>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<SoldeClient>(SoldeClient.class));
    }

}
