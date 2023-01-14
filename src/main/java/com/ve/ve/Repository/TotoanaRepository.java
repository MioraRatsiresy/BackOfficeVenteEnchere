package com.vehicule.vehicule.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicule.vehicule.DAO.TotoanaDAO;
import com.vehicule.vehicule.Model.RequeteNonEnvoye;
import com.vehicule.vehicule.Model.SyncAnnotation;
import com.vehicule.vehicule.Model.Totoana;

@Repository
public class TotoanaRepository implements TotoanaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RequeteNonEnvoyeRepository requete;

    @Override
    public ArrayList<Totoana> getTotoana(String id) {
        String sql = "select * from totoana where idpoint='" + id + "'";
        return (ArrayList<Totoana>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Totoana>(Totoana.class));

    }

    @Override
    public void insertTotoana(Totoana toto) {
        String sql = "INSERT INTO Totoana values (concat('Server2-Totoana-',nextval('totoanasequence')),'"
                + toto.getIdpoint() + "'," + toto.getQuantiteTotoana() + ",'" + toto.getDateheuredebut() + "','"
                + toto.getDateheurefin() + "',0)";
        jdbcTemplate.update(sql);

        if (Totoana.class.getAnnotation(SyncAnnotation.class).sync()) {
            // synchronisation
            String id = this.getLastIdTotoana().get(0).getId();
            // sqlserver
            RequeteNonEnvoye rq = new RequeteNonEnvoye();
            String sql2 = "INSERT INTO Totoana values (|" + id + "|,|" + toto.getIdpoint() + "|,"
                    + toto.getQuantiteTotoana() + ",CONVERT(DATETIME,|" + toto.getDateheuredebut()
                    + "|,121),CONVERT(DATETIME,|" + toto.getDateheurefin() + "|,121),0)";
            String sql1 = "insert into Totoana(id,idpoint,quantitetotoana,dateheuredebut,dateheurefin) values (|" + id
                    + "|,|" + toto.getIdpoint() + "|," + toto.getQuantiteTotoana() + ",|" + toto.getDateheuredebut()
                    + "|,|" + toto.getDateheurefin() + "|)";
            rq.setRequest1(sql1);
            rq.setRequest2(sql2);
            requete.insertRequeteNonEnvoye(rq);
        }

    }

    @Override
    public void updateTotoana(Totoana toto) {
        String sql = "Update Totoana set idpoint=" + toto.getIdpoint() + ",quantitetotoana=" + toto.getQuantiteTotoana()
                + ",dateheuredebut='" + toto.getDateheuredebut() + "',dateheurefin='" + toto.getDateheurefin()
                + "' where id=" + toto.getId();
        jdbcTemplate.update(sql);

    }

    @Override
    public void deleteTotoana(String id) {
        String sql = "UPDATE TOTOANA set etat=5 where id='" + id + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public ArrayList<Totoana> getLastIdTotoana() {
        String sql = "select * from totoana where id LIKE 'Server2%' order by split_part(id, '-', 3) DESC LIMIT 1";
        return (ArrayList<Totoana>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Totoana>(Totoana.class));

    }

}
