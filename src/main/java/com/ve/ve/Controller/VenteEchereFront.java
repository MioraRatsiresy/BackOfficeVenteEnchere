package com.ve.ve.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ve.ve.Repository.AdminLoginRepository;
import com.ve.ve.Repository.CategorieRepository;

@Controller
public class VenteEchereFront {
    @Autowired
    private AdminLoginRepository user;

    @Autowired 
    private CategorieRepository categorie;
    

 

}
