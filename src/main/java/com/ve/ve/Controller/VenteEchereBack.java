package com.ve.ve.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ve.ve.Model.AdminLogin;
import com.ve.ve.Model.Categorie;
import com.ve.ve.Repository.AdminLoginRepository;
import com.ve.ve.Repository.CategorieRepository;

@Controller
public class VenteEchereBack {
    @Autowired
    private AdminLoginRepository user;

    @Autowired
    private CategorieRepository categorie;

    /* LOGIN */
    @RequestMapping(value = "/login/traitement", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String status = null;
        String retour = "";
        String adminUser = request.getParameter("adminUser");
        String pwd = request.getParameter("pwd");
        System.out.println("adminUser : " + adminUser);
        System.out.println("Mot de passe : " + pwd);
        ArrayList<AdminLogin> resultat = user.getAdminLogin(adminUser, pwd);
        for (int i = 0; i < resultat.size(); i++) {
            System.out.println(resultat.get(i).getAdminUser());
        }
        if (resultat.size() == 1) {
            HttpSession session = request.getSession();
            map.put("iduser", resultat.get(0).getId());
            session.setAttribute("idUser", resultat.get(0).getId());
            retour = "Login correcte";
            status = "succes";
        } else {
            retour = "Login incorrecte";
            status = "erreur";
        }
        map.put("message", retour);
        map.put("status", status);
        return map;
    }

    @RequestMapping(value = "/incription", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> inscriptionAdmin(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        AdminLogin admin = new AdminLogin();
        admin.setAdminUser(request.getParameter("adminlogin"));
        admin.setPwd(request.getParameter("pwd"));
        user.insertAdminLogin(admin);
        return map;
    }

    @RequestMapping(value = "/insertcategorie", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void insertCategorie(HttpServletRequest request) {
        Categorie cat = new Categorie();
        cat.setCategorie(request.getParameter("categorie"));
        categorie.insertCategorie(cat);
    }

    @RequestMapping(value = "/listecategorie", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> listeCategorie(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("categorie", categorie.getCategorie());
        return map;
    }

    /**************************************************************************************************************/
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/afficherListeCategorie")
    public String afficherListeCategorie(Model model) {
        ArrayList<Categorie> listeCategorie = categorie.getCategorie();
        model.addAttribute("categorie", listeCategorie);
        return "listeCategorie";
    }

}
