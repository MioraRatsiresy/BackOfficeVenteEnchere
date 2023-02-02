package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.Client;
import com.ve.ve.Model.CompteClient;

public interface ClientDAO {
    ArrayList<Client> getClient();

    ArrayList<Client> verifyLogin(String identifiant,String pwd);

    void inscriptionClient(Client client);

    void rechargerMonCompte(CompteClient compte);

    ArrayList<Client> getClientById(int idClient);

}
