package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.Enchere;
import com.ve.ve.Model.MesEncheres;

public interface EnchereDAO {
    ArrayList<Enchere> getListeEnchere();
    ArrayList<Enchere> getMesEncheres(int id);

    ArrayList<MesEncheres> searchEnchere(String search);

    void insertEnchere(Enchere enchere);
}
