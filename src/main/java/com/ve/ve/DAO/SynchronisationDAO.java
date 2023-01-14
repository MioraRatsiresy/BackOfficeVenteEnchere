package com.vehicule.vehicule.DAO;

import java.util.ArrayList;

import com.vehicule.vehicule.Model.RequeteEnvoye;
import com.vehicule.vehicule.Model.RequeteNonEnvoye;
import com.vehicule.vehicule.Model.RequeteRecu;
import com.vehicule.vehicule.Model.RequeteRecuExecute;

public interface SynchronisationDAO {
    void insertRequeteEnvoye(RequeteEnvoye env);

    void insertRequeteRecu(RequeteRecu recu);
    void insertRequeteRecuExecute(RequeteRecuExecute recuexecute);
    ArrayList<RequeteNonEnvoye> getRequeteNonEnvoye();
    void updateRequeteNonEnvoye(int id,int serveur);
    ArrayList<RequeteRecu> getRequeteRecu();
    void insertrequete(String requete);
}
