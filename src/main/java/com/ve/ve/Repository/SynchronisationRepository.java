package com.vehicule.vehicule.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicule.vehicule.DAO.SynchronisationDAO;
import com.vehicule.vehicule.Model.RequeteEnvoye;
import com.vehicule.vehicule.Model.RequeteNonEnvoye;
import com.vehicule.vehicule.Model.RequeteRecu;
import com.vehicule.vehicule.Model.RequeteRecuExecute;

@Repository
public class SynchronisationRepository implements SynchronisationDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void insertRequeteEnvoye(RequeteEnvoye env) {
        String sql="INSERT INTO RequeteEnvoye values (nextval('requeteenvoyesequence'),"+env.getIdrequestnotsend()+")";
        jdbcTemplate.update(sql);
        
    }

    @Override
    public void insertRequeteRecu(RequeteRecu recu) {
        String sql="INSERT INTO RequeteRecu values (nextval('requeterecusequence'),'"+recu.getRequest()+"',"+recu.getServeur()+")";
        jdbcTemplate.update(sql);
        
    }

    @Override
    public void insertRequeteRecuExecute(RequeteRecuExecute recuexecute) {
        String sql="INSERT INTO RequeteRecuExecute values (nextval('requeterecuexecutesequence'),"+recuexecute.getRecu()+")";
        jdbcTemplate.update(sql);
        
    }

    @Override
    public ArrayList<RequeteNonEnvoye> getRequeteNonEnvoye() {
        String sql = "select * from requetenonenvoye where id not in (select idrequestnotsend from requeteenvoye)";
        return (ArrayList<RequeteNonEnvoye>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<RequeteNonEnvoye>(RequeteNonEnvoye.class));
    }
    @Override
    public void updateRequeteNonEnvoye(int id,int serveur) {
        String sql="Update INTO RequeteNonEnvoye set etat="+serveur+" where id="+id;
        jdbcTemplate.update(sql);
        
    }
    @Override
    public ArrayList<RequeteRecu> getRequeteRecu() {
        String sql = "select * from requeterecu where id not in (select recu from requeterecuexecute)";
        return (ArrayList<RequeteRecu>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<RequeteRecu>(RequeteRecu.class));
    }

    @Override
    public void insertrequete(String requete) {
        jdbcTemplate.update(requete);
        
    }
    
}
