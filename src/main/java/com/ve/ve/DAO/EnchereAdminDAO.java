package com.ve.ve.DAO;

import java.util.ArrayList;

import com.ve.ve.Model.EnchereAdmin;

public interface EnchereAdminDAO {
    ArrayList<EnchereAdmin> getAll();
    void insertEnchereAdmin(EnchereAdmin enchereAdmin);
    void updateEnchereAdmin(int id, double duree);
    ArrayList<EnchereAdmin> getEnchereAdminById(int id);
}
