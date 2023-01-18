package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.Categorie;
import com.ve.ve.Model.Commission;

public interface CategorieDAO {
    ArrayList<Categorie> getCategorie();

    void insertCategorie(Categorie admin);

    void updateCommission(Commission com);

    ArrayList<Commission> getCommission();

    ArrayList<Categorie> getCategorieSansDuree();

}
