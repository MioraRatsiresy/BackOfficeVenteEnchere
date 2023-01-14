package com.vehicule.vehicule.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicule.vehicule.DAO.ExplosionDAO;
import com.vehicule.vehicule.Model.Explosion;
import com.vehicule.vehicule.Model.PointCarriere;
import com.vehicule.vehicule.Model.RequeteNonEnvoye;
import com.vehicule.vehicule.Model.SyncAnnotation;

@Repository
public class ExplosionRepository implements ExplosionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RequeteNonEnvoyeRepository requete;

    @Override
    public ArrayList<Explosion> getExplosion(String id) {
        String sql = "select * from explosion where idpoint='" + id + "'";
        return (ArrayList<Explosion>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Explosion>(Explosion.class));
    }

    @Override
    public void insertExplosion(Explosion explosion) {
        String sql = "INSERT INTO Explosion values (concat('Server2-Explosion-',nextval('explosionsequence')),'"
                + explosion.getIdpoint() + "','" + explosion.getDateexplosion() + "','" + explosion.getQuantiteVato()
                + "',0)";
        jdbcTemplate.update(sql);

        if (Explosion.class.getAnnotation(SyncAnnotation.class).sync()) {
            // synchronisation
            String id = this.getLastIdExplosion().get(0).getId();
            // sqlserver
            RequeteNonEnvoye rq = new RequeteNonEnvoye();
            String sql2 = "INSERT INTO Explosion values (|" + id + "|,|" + explosion.getIdpoint() + "|,convert(date,|"
                    + explosion.getDateexplosion() + "|,23),|" + explosion.getQuantiteVato() + "|,0)";
            String sql1 = "insert into Explosion(id,dateexplosion,quantiteVato,idpoint) values(|" + id + "|,|"
                    + explosion.getDateexplosion() + "|,|" + explosion.getQuantiteVato() + "|,|"
                    + explosion.getIdpoint() + "|)";
            rq.setRequest1(sql1);
            rq.setRequest2(sql2);
            requete.insertRequeteNonEnvoye(rq);
        }
    }

    @Override
    public void updateExplosion(Explosion explosion) {
        String sql = "Update explosion set idpoint='" + explosion.getIdpoint() + "',dateexplosion='"
                + explosion.getDateexplosion() + "',quantitevato='" + explosion.getQuantiteVato() + "') where id="
                + explosion.getId();
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteExplosion(String id) {
        String sql = "Update explosion set etat=5 where id='" + id + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public ArrayList<Explosion> getLastIdExplosion() {
        String sql = "select * from explosion where id LIKE 'Server2%' order by split_part(id, '-', 3) DESC LIMIT 1";
        return (ArrayList<Explosion>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Explosion>(Explosion.class));
    }
}
