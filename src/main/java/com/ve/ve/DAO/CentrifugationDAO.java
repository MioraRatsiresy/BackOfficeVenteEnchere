package com.vehicule.vehicule.DAO;

import java.util.ArrayList;

import com.vehicule.vehicule.Model.Centrifugation;

public interface CentrifugationDAO {
    ArrayList<Centrifugation> getCentrifugation(String id);

    ArrayList<Centrifugation> getLastIdCentrifugation();

    void insertCentrifugation(Centrifugation centrifug);

    void updateCentrifugation(Centrifugation Centrifug);

    void deleteCentrifugation(String id); 
}
