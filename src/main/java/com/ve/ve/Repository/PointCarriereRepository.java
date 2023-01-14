package com.vehicule.vehicule.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicule.vehicule.DAO.PointCarriereDAO;
import com.vehicule.vehicule.Model.PointCarriere;
import com.vehicule.vehicule.Model.RequeteNonEnvoye;
import com.vehicule.vehicule.Model.SyncAnnotation;


@Repository
public class PointCarriereRepository implements PointCarriereDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired 
    private RequeteNonEnvoyeRepository requete;

    @Override
    public ArrayList<PointCarriere> getListePointCarriere() {
        String sql = "select * from pointcarriere";
        return (ArrayList<PointCarriere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<PointCarriere>(PointCarriere.class));

    }

    @Override
    public void insertPointCarriere(PointCarriere point) {
       String sql="INSERT INTO PointCarriere values (concat('Server2-PointCarriere-',nextval('pointcarrieresequence')),"+point.getXposition()+","+point.getYposition()+",'"+point.getNompoint()+"',0)";
       jdbcTemplate.update(sql);

       if(PointCarriere.class.getAnnotation(SyncAnnotation.class).sync()){
       // synchronisation
       String id=this.getLastIdPointCarriere().get(0).getId();
       //sqlserver
       RequeteNonEnvoye rq=new RequeteNonEnvoye();
       String sql2="INSERT INTO PointCarriere values (|"+id+"|,"+point.getXposition()+","+point.getYposition()+",|"+point.getNompoint()+"|,0)";
       String sql1="insert into PointCarriere(id,nompoint,xposition,yposition) values(|"+id+"|,|"+point.getNompoint()+"|,"+point.getXposition()+","+point.getYposition()+")";
       rq.setRequest1(sql1);
       rq.setRequest2(sql2);
       requete.insertRequeteNonEnvoye(rq);
       }
    }

    @Override
    public void updatePointCarriere(PointCarriere point) {
        String sql="update Pointcarriere set xposition="+point.getXposition()+",ypostion="+point.getYposition()+",nompoint='"+point.getNompoint()+"' where id="+point.getId();
        jdbcTemplate.update(sql);        
    }

    @Override
    public ArrayList<PointCarriere> getLastIdPointCarriere() {
        String sql = "select * from pointcarriere where id LIKE 'Server2%' order by split_part(id, '-', 3) DESC LIMIT 1";
        return (ArrayList<PointCarriere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<PointCarriere>(PointCarriere.class));
    }
    public int getIdPointSqlite(int id){
        String sql="select * from pointcarriere where envoyeur1="+id;
        System.out.println(sql);
        ArrayList<PointCarriere> point=(ArrayList<PointCarriere>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<PointCarriere>(PointCarriere.class));
        return 0;

    }
}
