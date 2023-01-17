package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.AdminLoginDAO;
import com.ve.ve.Model.AdminLogin;
import com.ve.ve.Model.CompteClient;

@Repository
public class AdminLoginRepository implements AdminLoginDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public ArrayList<AdminLogin> getAdminLogin(String user,String pwd) {
        String sql = "select * from adminlogin where adminUser='"+user+"' and pwd=md5('"+pwd+"')";
        return (ArrayList<AdminLogin>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<AdminLogin>(AdminLogin.class));
    }

    @Override
    public void insertAdminLogin(AdminLogin admin) {
        String sql="INSERT INTO adminlogin values (default,'"+admin.getAdminUser()+"',md5('"+admin.getPwd()+"'))";
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateAdminLogin(AdminLogin admin) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAdminLogin(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void validerRechargementCompte(int id, int action) {
        String sql="update compteclient set etat="+action+" where id="+id;
        jdbcTemplate.update(sql);
        
    }

    @Override
    public ArrayList<CompteClient> getListeDemande() {
        String sql = "select * from compteclient where etat=0";
        return (ArrayList<CompteClient>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<CompteClient>(CompteClient.class));

    }
    
}
