package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.AdminLogin;
import com.ve.ve.Model.CompteClient;


public interface AdminLoginDAO {
    ArrayList<AdminLogin> getAdminLogin(String user,String pwd);

    void insertAdminLogin(AdminLogin admin);

    void updateAdminLogin(AdminLogin admin);

    void deleteAdminLogin(int id); 

    void validerRechargementCompte(int id,int action); //1 valider 5 refuser

    ArrayList<CompteClient> getListeDemande();

}
