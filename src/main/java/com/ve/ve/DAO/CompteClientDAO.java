package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.SoldeClient;

public interface CompteClientDAO {
    ArrayList<SoldeClient> getSolde(int idClient);

}
