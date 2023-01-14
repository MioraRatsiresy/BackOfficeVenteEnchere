package com.vehicule.vehicule.DAO;

import java.util.ArrayList;

import com.vehicule.vehicule.Model.PointCarriere;


public interface PointCarriereDAO{
    ArrayList<PointCarriere> getListePointCarriere();
    ArrayList<PointCarriere> getLastIdPointCarriere();

    void insertPointCarriere(PointCarriere point);

    void updatePointCarriere(PointCarriere point);

}
