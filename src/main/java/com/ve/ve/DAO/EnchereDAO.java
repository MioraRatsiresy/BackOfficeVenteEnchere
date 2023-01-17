package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.Enchere;

public interface EnchereDAO {
    ArrayList<Enchere> getListeEnchere();

    ArrayList<Enchere> searchEnchere(String search);
}
