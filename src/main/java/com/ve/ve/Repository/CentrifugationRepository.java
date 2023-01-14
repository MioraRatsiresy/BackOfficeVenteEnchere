package com.vehicule.vehicule.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicule.vehicule.DAO.CentrifugationDAO;
import com.vehicule.vehicule.Model.Centrifugation;
import com.vehicule.vehicule.Model.RequeteNonEnvoye;
import com.vehicule.vehicule.Model.SyncAnnotation;

@Repository
public class CentrifugationRepository implements CentrifugationDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RequeteNonEnvoyeRepository requete;

    @Override
    public ArrayList<Centrifugation> getCentrifugation(String id) {
        String sql = "select * from centrifugation where idpoint='" + id+"'";
        return (ArrayList<Centrifugation>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Centrifugation>(Centrifugation.class));
    }

    @Override
    public void insertCentrifugation(Centrifugation centrifug) {
        String sql = "INSERT INTO Centrifugation(id,idpoint,quantitevato,quantitevolamena,dateheuredebut,dateheurefin) values (concat('Server2-Centrifugation-',nextval('centrifugationsequence')),'" + centrifug.getIdpoint() + "',"
                + centrifug.getQuantiteVato() + "," + centrifug.getQuantiteVolamena() + ",'"
                + centrifug.getDateheuredebut() + "','" + centrifug.getDateheurefin() + "')";
        jdbcTemplate.update(sql);

        if(Centrifugation.class.getAnnotation(SyncAnnotation.class).sync()){
        // synchronisation
        String id = this.getLastIdCentrifugation().get(0).getId();
        // sqlserver
        RequeteNonEnvoye rq = new RequeteNonEnvoye();
        String sql2 = "INSERT INTO Centrifugation values (|"+id+"|,|"+ centrifug.getIdpoint() + "|," + centrifug.getQuantiteVato() + "," + centrifug.getQuantiteVolamena()+ ",CONVERT(DATETIME,|" + centrifug.getDateheuredebut() + "|,121),CONVERT(DATETIME,|"+ centrifug.getDateheurefin() + "|,121),0)";
        String sql1 = "insert into Centrifugation(id,idpoint,quantitevato,quantitevolamena,dateheuredebut,dateheurefin,etat) values(|"+id+"|,|" + centrifug.getIdpoint() + "|,"
        + centrifug.getQuantiteVato() + "," + centrifug.getQuantiteVolamena() + ",|"
        + centrifug.getDateheuredebut() + "|,|" + centrifug.getDateheurefin() + "|)";
        rq.setRequest1(sql1);
        rq.setRequest2(sql2);
        requete.insertRequeteNonEnvoye(rq);
        }

    }

    @Override
    public void updateCentrifugation(Centrifugation centrifug) {
        String sql = "UPDATE Centrifugation set idpoint=" + centrifug.getIdpoint() + ",quantitevato="+ centrifug.getQuantiteVato() + ",quantitevolamena=" + centrifug.getQuantiteVolamena()+ ",dateheuredebut='" + centrifug.getDateheuredebut() + "',dateheurefin='" + centrifug.getDateheurefin()+ "')";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteCentrifugation(String id) {
        String sql = "UPDATE Centrifugation set etat=5 where id='" + id+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public ArrayList<Centrifugation> getLastIdCentrifugation() {
        String sql = "select * from centrifugation where id LIKE 'Server2%' order by split_part(id, '-', 3) desc limit 1";
        return (ArrayList<Centrifugation>) jdbcTemplate.query(sql,
        new BeanPropertyRowMapper<Centrifugation>(Centrifugation.class));
    }

}
