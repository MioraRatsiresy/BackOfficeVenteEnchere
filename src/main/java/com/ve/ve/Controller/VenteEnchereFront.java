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
import com.ve.ve.Model.EncherePlafond;
import com.ve.ve.Model.MesEncheres;
import com.ve.ve.Gestiontoken.GestionToken;
import com.ve.ve.Model.Client;
import com.ve.ve.Repository.ClientRepository;
import com.ve.ve.Repository.EncherePlafondRepository;
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

    @Autowired
    private EncherePlafondRepository plafond;

    @RequestMapping(value = "/listeEnchereFront", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> listeEnchere() {
        Map<String, Object> map = new HashMap<>();
        map.put("enchere",mesEncheresRepository.getListeEnchere());
        return map;
    }

    @RequestMapping(value = "/ficheenchere/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> ficheenchere(@PathVariable int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("fiche",mesEncheresRepository.getFicheEnchere(id));
        return map;
    }

    @RequestMapping(value = "/rechercheAvanceFront", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> rechercheAvance(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("Search : "+request.getParameter("search"));
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

    @RequestMapping(value = "/inscriptionClient", method = RequestMethod.POST, produces = "application/json")
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
        GestionToken tok = new GestionToken();
        ArrayList<Client> resultat = clientRepository.verifyLogin(request.getParameter("identifiant"), request.getParameter("pwd"));
        map.put("iduser", resultat.get(0).getId());
        map.put("token", tok.generateToken(resultat.get(0)));
        map.put("date d'expiration", tok.expirationdateToken(tok.generateToken(resultat.get(0))).toString());
        map.put("message", "Insertion effectu??e avec succ??s");
        map.put("status", "Succ??s");
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


    @RequestMapping(value = "/enchereplafond/{idenchere}" , method = RequestMethod.GET,produces="application/json")
	@ResponseBody
    @CrossOrigin
    public Map<String,Object> enchereplafond(HttpServletRequest request,@PathVariable int idenchere){
        Map<String,Object> map=new HashMap<>();
        map.put("plafond", plafond.getEncherePlafond(Integer.parseInt(request.getParameter("idclient")),idenchere));
        return map;
    }

    @RequestMapping(value = "/insertenchereplafond/{idenchere}" , method = RequestMethod.POST,produces="application/json")
	@ResponseBody
    @CrossOrigin
    public Map<String,Object> insertenchereplafond(HttpServletRequest request,@PathVariable int idenchere){
        Map<String,Object> map=new HashMap<>();
        EncherePlafond ench=new EncherePlafond();
        if(enchereRepository.verifyCompte(Integer.parseInt(request.getParameter("idclient")),Double.parseDouble(request.getParameter("montant")))){
        ench.setMontant(Double.parseDouble(request.getParameter("montant")));
        ench.setIntervalle(Double.parseDouble(request.getParameter("intervalle")));
        ench.setIdEnchere(idenchere);
        ench.setIdClient(Integer.parseInt(request.getParameter("idclient")));
        plafond.insertPlafond(ench);            
        }
        else{
            map.put("Erreur","Solde insuffisant");
        }
        return map;
    }
}
