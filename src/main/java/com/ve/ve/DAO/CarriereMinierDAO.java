package com.vehicule.vehicule.DAO;

import java.util.ArrayList;

import com.vehicule.vehicule.Model.CarriereMinier;

public interface CarriereMinierDAO {
    ArrayList<CarriereMinier> getCarriereMinier();


    void insertCarriereMinier(CarriereMinier personne);

    void updateCarriereMinier(CarriereMinier personne);

    void deleteCarriereMinier(int id); 
}
