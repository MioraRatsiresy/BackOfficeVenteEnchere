package com.ve.ve.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ve.ve.Model.AdminLogin;
import com.ve.ve.Model.Categorie;
import com.ve.ve.Model.Commission;
import com.ve.ve.Model.EnchereAdmin;
import com.ve.ve.Model.Produit;
import com.ve.ve.Model.StatistiqueCategorie;
import com.ve.ve.Model.StatistiqueChiffreAffaire;
import com.ve.ve.Repository.AdminLoginRepository;
import com.ve.ve.Repository.CategorieRepository;
import com.ve.ve.Repository.EnchereAdminRepository;
import com.ve.ve.Repository.ProduitRepository;
import com.ve.ve.Repository.StatistiqueCategorieRepository;
import com.ve.ve.Repository.StatistiqueChiffreAffaireRepository;

@Controller
public class VenteEchereBack {
    @Autowired
    private AdminLoginRepository user;

    @Autowired
    private CategorieRepository categorie;

    @Autowired
    private StatistiqueCategorieRepository statistiqueCategorie;

    @Autowired
    private StatistiqueChiffreAffaireRepository statistiqueChiffreAffaire;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private EnchereAdminRepository enchereAdminRepository;

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

    /********************************************
     * MIORA
     ******************************************************************/
    @GetMapping("/index")
    @CrossOrigin
    public String index() {
        return "index";
    }

    @GetMapping("/accueil")
    @CrossOrigin
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/afficherListeCategorie")
    @CrossOrigin
    public String afficherListeCategorie(Model model) {
        ArrayList<Categorie> listeCategorie = categorie.getCategorie();
        model.addAttribute("categorie", listeCategorie);
        return "listeCategorie";
    }

    /* Miora */
    /* LOGOUT */

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    // @GetMapping("/logout")
    public Map<String, String> deconnexion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        Map<String, String> map = new HashMap<>();
        map.put("message", "Logout with success");
        map.put("status", "200");
        return map;
    }

    /* MBOLA */
    @GetMapping("/statistiqueCategorie")
    @CrossOrigin
    public String viewStatistique() {
        return "statistique";
    }

    @RequestMapping("/statistique")
    @ResponseBody
    @CrossOrigin
    // public ResponseEntity<?> getDataForPiechart(){
    public String getDataForPiechart() {
        // ArrayList<StatistiqueCategorie> piechartData = statistiqueCategorie.getAll();
        // return new ResponseEntity<>(piechartData, HttpStatus.OK);
        ArrayList<StatistiqueCategorie> dataList = statistiqueCategorie.getAll();
        JsonArray jsonArrayCategory = new JsonArray();
        JsonArray jsonArraySeries = new JsonArray();
        JsonArray jsonArraymois = new JsonArray();
        JsonArray jsonArraymontant = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        dataList.forEach(data -> {
            jsonArrayCategory.add(data.getNombre());
            jsonArraySeries.add(data.getCategorie());
        });
        ArrayList<StatistiqueChiffreAffaire> dataList1 = statistiqueChiffreAffaire.getAll();
        dataList1.forEach(data -> {
            jsonArraymois.add(data.getNomMois());
            jsonArraymontant.add(data.getMontant());
        });
        jsonObject.add("nombre", jsonArrayCategory);
        jsonObject.add("categorie", jsonArraySeries);
        jsonObject.add("mois", jsonArraymois);
        jsonObject.add("montant", jsonArraymontant);
        return jsonObject.toString();
    }

    @RequestMapping("/linechartdata")
    @ResponseBody
    @CrossOrigin
    public String getDataFromDB() {
        ArrayList<StatistiqueChiffreAffaire> dataList = statistiqueChiffreAffaire.getAll();
        JsonArray jsonArrayCategory = new JsonArray();
        JsonArray jsonArraySeries = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        dataList.forEach(data -> {
            jsonArrayCategory.add(data.getNomMois());
            jsonArraySeries.add(data.getMontant());
        });
        jsonObject.add("categories", jsonArrayCategory);
        jsonObject.add("series", jsonArraySeries);
        return jsonObject.toString();
    }

    /* MIORA */
    @GetMapping("/validationCompte")
    @CrossOrigin
    // @ResponseBody
    public String validationCompte(Model model) {
        model.addAttribute("demande", user.getListeDemande());
        return "rechargementCompte";
    }

    @PutMapping("/validerdemande/{id}/{etat}")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> validerdemande(@PathVariable int id, @PathVariable int etat) {
        Map<String, Object> map = new HashMap<>();
        user.validerRechargementCompte(id, etat);
        map.put("Status", "Success");
        return map;
    }

    @GetMapping("/voircommission")
    @CrossOrigin
    public String voircommission(Model model) {
        model.addAttribute("commission", categorie.getCommission());
        return "commission";
    }

    @PutMapping("/updatecommission/{pourcentage}")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> updateCommission(@PathVariable double pourcentage) {
        Map<String, Object> map = new HashMap<>();
        Commission com = new Commission();
        com.setPourcentage(pourcentage);
        categorie.updateCommission(com);
        map.put("Status", "Success");
        return map;
    }

    @GetMapping("/afficherListeProduit")
    @CrossOrigin
    public String afficherListeProduit(Model model) {
        ArrayList<Produit> listeProduit = produitRepository.getAll();
        model.addAttribute("produit", listeProduit);
        ArrayList<Categorie> listeCategorie = categorie.getCategorie();
        model.addAttribute("categorie", listeCategorie);
        return "listeProduit";
    }

    @RequestMapping(value = "/insertProduit", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void insertProduit(HttpServletRequest request) {
        Produit produit = new Produit();
        produit.setProduit(request.getParameter("produit"));
        produit.setCategorie(Integer.parseInt(request.getParameter("categorie")));
        produitRepository.insertProduit(produit);
    }

    @RequestMapping(value = "/listeProduit", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> listeProduit(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("produit", produitRepository.getAll());
        return map;
    }

    @GetMapping("/listeEnchereAdmin")
    @CrossOrigin
    public String listeEnchereAdmin(Model model) {
        ArrayList<EnchereAdmin> listeEnchereAdmin = enchereAdminRepository.getAll();
        model.addAttribute("enchereAdmin", listeEnchereAdmin);
        ArrayList<Categorie> listeCategorie = categorie.getCategorieSansDuree();
        model.addAttribute("categorie", listeCategorie);
        return "parametreDureeEnchere";
    }

    @RequestMapping(value = "/insertEnchereAdmin", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void insertEnchereAdmin(HttpServletRequest request) {
        EnchereAdmin enchereAdmin = new EnchereAdmin();
        enchereAdmin.setCategorieId(Integer.parseInt(request.getParameter("categorie")));
        enchereAdmin.setDuree(Double.parseDouble(request.getParameter("duree")));
        enchereAdminRepository.insertEnchereAdmin(enchereAdmin);
    }

    @RequestMapping(value = "/listeEnchereAdminWs", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> listeEnchereAdminWs(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("enchereAdmin", enchereAdminRepository.getAll());
        return map;
    }

    @RequestMapping(value = "/updateEnchereAdminWs", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void updateEnchereAdmin(HttpServletRequest request) {
        double duree = Double.parseDouble(request.getParameter("duree"));
        int id = Integer.parseInt(request.getParameter("id"));
        enchereAdminRepository.updateEnchereAdmin(id, duree);
    }

    @RequestMapping("/getInfoToUpdate")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<?> getInfoToUpdate(HttpServletRequest request) {
        ArrayList<EnchereAdmin> liste = enchereAdminRepository
                .getEnchereAdminById(Integer.parseInt(request.getParameter("id")));
        return new ResponseEntity<>(liste, HttpStatus.OK);
    }

}
