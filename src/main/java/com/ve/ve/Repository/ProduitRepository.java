package com.ve.ve.Repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ve.ve.DAO.ProduitDAO;
import com.ve.ve.Model.Produit;

@Repository
public class ProduitRepository implements ProduitDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Produit> getAll() {
        String sql = "select p.*, c.categorie as nomCategorie from produit p join categorie c on c.id=p.categorie";
        return (ArrayList<Produit>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Produit>(Produit.class));
    }

    @Override
    public void insertProduit(Produit produit) {
        String sql="INSERT INTO produit values (default,'"+produit.getProduit()+"',"+produit.getCategorie()+")";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

}
