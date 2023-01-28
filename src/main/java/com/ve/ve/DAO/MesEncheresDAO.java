package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.Enchere;
import com.ve.ve.Model.MesEncheres;

public interface MesEncheresDAO {
    ArrayList<Enchere> getListeEnchere();
    ArrayList<MesEncheres> getMesEncheres(int id);
}
