package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.ClientDAO;
import com.ve.ve.Model.Client;
import com.ve.ve.Model.CompteClient;

@Repository
public class ClientRepository implements ClientDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public ArrayList<Client> getClient() {
        String sql = "select * from client";
        return (ArrayList<Client>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Client>(Client.class));
    }

    @Override
    public ArrayList<Client> verifyLogin(String identifiant,String pwd) {
        String sql = "select * from client where identifiant='"+identifiant+"' and pwd=md5('"+pwd+"')";
        return (ArrayList<Client>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Client>(Client.class));
    }

    @Override
    public void inscriptionClient(Client client) {
        String sql="INSERT INTO client values (default,'"+client.getNom()+"','"+client.getPrenom()+"','"+client.getContact()+"','"+client.getIdentifiant()+"',md5('"+client.getPwd()+"'))";
        jdbcTemplate.update(sql);
        
    }

    @Override
    public void rechargerMonCompte(CompteClient compte) {
        String sql="INSERT INTO compteclient values (default,'"+compte.getMontant()+"','"+compte.getEtat()+"','"+compte.getClientid()+"','"+compte.getActionTransaction()+"')";
        jdbcTemplate.update(sql);
        
    }

    @Override
    public ArrayList<Client> getClientById(int idClient) {
        String sql = "select * from client where id="+idClient;
        return (ArrayList<Client>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Client>(Client.class));
    }
    
}
