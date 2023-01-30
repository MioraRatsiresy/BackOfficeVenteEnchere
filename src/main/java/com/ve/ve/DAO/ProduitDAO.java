package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.Produit;

public interface ProduitDAO {
    ArrayList<Produit> getAll();
    void insertProduit(Produit produit);
    ArrayList<Produit> getProduitByCategorie(int idCategorie);
}
