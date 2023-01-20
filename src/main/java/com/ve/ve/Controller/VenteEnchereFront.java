package com.ve.ve.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ve.ve.Model.Enchere;
import com.ve.ve.Model.Client;
import com.ve.ve.Repository.ClientRepository;
import com.ve.ve.Repository.EnchereRepository;

@Controller
public class VenteEnchereFront {

    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "/listeEnchereFront", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> listeEnchere() {
        Map<String, Object> map = new HashMap<>();
        map.put("enchere", enchereRepository.getListeEnchere());
        return map;
    }

    @RequestMapping(value = "/rechercheAvanceFront", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> rechercheAvance(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("enchere", enchereRepository.searchEnchere(request.getParameter("search")));
        return map;
    }

    @RequestMapping(value = "/getMesEncheres", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> getMesEncheres(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        map.put("enchere", enchereRepository.getMesEncheres(Integer.parseInt(request.getParameter("id"))));
        return map;
    }

    @RequestMapping(value = "/insertEnchere", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void insertEnchere(HttpServletRequest request) {
        Enchere enchere = new Enchere();
        enchere.setProduit(Integer.parseInt(request.getParameter("produit")));
        enchere.setLibelle(request.getParameter("libelle"));
        enchere.setPrixMin(Double.parseDouble(request.getParameter("prixMin")));
        enchere.setDuree(Double.parseDouble(request.getParameter("duree")));
        enchere.setIdclient(Integer.parseInt(request.getParameter("idClient")));
        enchere.setEtat("0");
        enchereRepository.insertEnchere(enchere);
    }

    @RequestMapping(value = "/incriptionClient", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> inscriptionClient(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Client client = new Client();
        client.setNom(request.getParameter("nom"));
        client.setPrenom(request.getParameter("prenom"));
        client.setContact(request.getParameter("contact"));
        client.setIdentifiant(request.getParameter("identifiant"));
        client.setPwd(request.getParameter("pwd"));
        clientRepository.inscriptionClient(client);
        map.put("message", "Insertion effectuée avec succès");
        map.put("status", "Succès");
        return map;
    }

    @RequestMapping(value = "/login/traitementClient", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String status = null;
        String retour = "";
        String adminUser = request.getParameter("identifiant");
        String pwd = request.getParameter("pwd");
        System.out.println("adminUser : " + adminUser);
        System.out.println("Mot de passe : " + pwd);
        ArrayList<Client> resultat = clientRepository.verifyLogin(adminUser, pwd);
        if (resultat.size() == 1) {
            HttpSession session = request.getSession();
            map.put("iduser", resultat.get(0).getId());
            session.setAttribute("idClient", resultat.get(0).getId());
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
}
