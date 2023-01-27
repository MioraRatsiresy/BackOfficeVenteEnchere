package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.Enchere;

public interface MesEncheresDAO {
    ArrayList<Enchere> getListeEnchere();
    ArrayList<Enchere> getMesEncheres(int id);
}
