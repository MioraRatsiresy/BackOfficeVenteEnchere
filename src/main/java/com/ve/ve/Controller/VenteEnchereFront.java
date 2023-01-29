package com.ve.ve.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ve.ve.Model.Enchere;
import com.ve.ve.Model.MesEncheres;
import com.ve.ve.Gestiontoken.GestionToken;
import com.ve.ve.Model.Client;
import com.ve.ve.Repository.ClientRepository;
import com.ve.ve.Repository.EnchereRepository;
import com.ve.ve.Repository.MesEncheresRepository;
import com.ve.ve.Repository.PhotoEnchereRepository;

import io.jsonwebtoken.Claims;

@Controller
public class VenteEnchereFront {

    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private MesEncheresRepository mesEncheresRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PhotoEnchereRepository photo;

    @RequestMapping(value = "/listeEnchereFront", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> listeEnchere() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<MesEncheres> enchere=mesEncheresRepository.getListeEnchere();
        System.out.println(enchere.get(3).getLibelle());
        System.out.println(enchere.get(3).getStatut());
        for(int i=0;i<enchere.size();i++){
            enchere.get(i).setPhotos(photo.findByIdEnchere(i));
        }
        map.put("enchere",enchere);
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

    @RequestMapping(value = "/getMesEncheres/{id}/{token}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> getMesEncheres(HttpServletRequest request,@PathVariable int id, @PathVariable String token) {
        Map<String, Object> map = new HashMap<>();
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(token);
             map.put("enchere", mesEncheresRepository.getMesEncheres(id));
        }
        catch(Exception e){
            map.put("Erreur",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/insertEnchere/{id}/{token}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void insertEnchere(HttpServletRequest request,@PathVariable int id, @PathVariable String token) {
        Map<String, Object> map = new HashMap<>();
        Enchere enchere = new Enchere();
        enchere.setProduit(Integer.parseInt(request.getParameter("produit")));
        enchere.setLibelle(request.getParameter("libelle"));
        enchere.setPrixMin(Double.parseDouble(request.getParameter("prixMin")));
        enchere.setDuree(Double.parseDouble(request.getParameter("duree")));
        enchere.setIdclient(id);
        enchere.setEtat("0");
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(token);
            enchereRepository.insertEnchere(enchere);
            map.put("response","Insertion avec succes");
        }
        catch(Exception e){ 
            map.put("Erreur",e.getMessage());
        }
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
            GestionToken tok=new GestionToken();
            map.put("token",tok.generateToken(resultat.get(0)));
            map.put("date d'expiration",tok.expirationdateToken(tok.generateToken(resultat.get(0))).toString());
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
    @RequestMapping(value = "/deconnexion" , method = RequestMethod.GET,produces="application/json")
	@ResponseBody
    @CrossOrigin
    public Map<String,String> deconnexion(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        Map<String,String> map=new HashMap<>();
        map.put("message","Logout with success");
        map.put("status","200");
        return map;
    }

}
