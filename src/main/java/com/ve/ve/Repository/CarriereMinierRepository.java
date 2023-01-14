package com.vehicule.vehicule.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicule.vehicule.DAO.CarriereMinierDAO;
import com.vehicule.vehicule.Model.CarriereMinier;

@Repository
public class CarriereMinierRepository implements CarriereMinierDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<CarriereMinier> getCarriereMinier() {
        String sql = "select * from carriereminier";
        return (ArrayList<CarriereMinier>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<CarriereMinier>(CarriereMinier.class));
    }

    @Override
    public void insertCarriereMinier(CarriereMinier carriere) {
        String sql="INSERT INTO CarriereMinier values (next value for carriereminiersequence,'"+carriere.getNom()+"')";
        jdbcTemplate.update(sql);
        
    }

    @Override
    public void updateCarriereMinier(CarriereMinier carriere) {
        String sql="UPDATE CarriereMinier SET nom='"+carriere.getNom()+"' where id="+carriere.getId();
        jdbcTemplate.update(sql);
        
    }

    @Override
    public void deleteCarriereMinier(int id) {
        // TODO Auto-generated method stub
        
    }
    
}
