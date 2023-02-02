package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.EnchereAdminDAO;
import com.ve.ve.Model.EnchereAdmin;

@Repository
public class EnchereAdminRepository implements EnchereAdminDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<EnchereAdmin> getAll() {
        String sql = "select e.*,c.categorie from enchereAdmin e join categorie c on c.id=e.categorieid";
        return (ArrayList<EnchereAdmin>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<EnchereAdmin>(EnchereAdmin.class));
    }

    @Override
    public void insertEnchereAdmin(EnchereAdmin enchereAdmin) {
        String sql = "INSERT INTO enchereAdmin values (default," + enchereAdmin.getCategorieId() + ","
                + enchereAdmin.getDuree() + ")";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateEnchereAdmin(int id, double duree) {
        String sql = "update enchereAdmin set duree=" + duree + " where id=" + id;
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    @Override
    public ArrayList<EnchereAdmin> getEnchereAdminById(int id) {
        String sql = "select*from enchereadmin where id="+id;
        return (ArrayList<EnchereAdmin>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<EnchereAdmin>(EnchereAdmin.class));
    }

}
