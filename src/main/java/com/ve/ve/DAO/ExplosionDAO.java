package com.vehicule.vehicule.DAO;

import java.util.ArrayList;

import com.vehicule.vehicule.Model.Explosion;

public interface ExplosionDAO {
    ArrayList<Explosion> getExplosion(String id);

    ArrayList<Explosion> getLastIdExplosion();

    void insertExplosion(Explosion explosion);

    void updateExplosion(Explosion explosion);

    void deleteExplosion(String id); 
}
