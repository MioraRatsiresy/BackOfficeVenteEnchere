package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.EncherePlafond;

public interface EncherePlafondDAO {
    ArrayList<EncherePlafond> getEncherePlafond(int idclient,int enchere);
    void insertPlafond(EncherePlafond plafond);
}
