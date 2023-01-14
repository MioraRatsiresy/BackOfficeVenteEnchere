package com.vehicule.vehicule.DAO;

import java.util.ArrayList;

import com.vehicule.vehicule.Model.Totoana;

public interface TotoanaDAO {
    ArrayList<Totoana> getTotoana(String id);

    ArrayList<Totoana> getLastIdTotoana();

    void insertTotoana(Totoana toto);

    void updateTotoana(Totoana toto);

    void deleteTotoana(String id); 
}
