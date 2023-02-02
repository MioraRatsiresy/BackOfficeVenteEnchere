package com.ve.ve.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ve.ve.Gestiontoken.GestionToken;
import com.ve.ve.Model.CompteClient;
import com.ve.ve.Model.Enchere;
import com.ve.ve.Repository.AdminLoginRepository;
import com.ve.ve.Repository.CategorieRepository;
import com.ve.ve.Repository.ClientRepository;
import com.ve.ve.Repository.MesEncheresRepository;
import com.ve.ve.Repository.ProduitRepository;

import io.jsonwebtoken.Claims;

@Controller
public class VenteEchereMobile {
    @Autowired
    private AdminLoginRepository user;

    @Autowired
    private CategorieRepository categorie;

    @Autowired
    private ClientRepository client;

    @Autowired
    private MesEncheresRepository mesEncheresRepository;

    @Autowired
    private ProduitRepository produitRepository;

    // @PostMapping("/token")
    // public String sendPnsToDevice(HttpServletRequest request) {
    // MessageNotification msg=new MessageNotification();
    // msg.setBody(request.getParameter("body"));
    // msg.setTarget(request.getParameter("target"));
    // msg.setTitle(request.getParameter("title"));
    // return this.sendPnsToDevice(msg);
    // }
    // public String sendPnsToDevice(MessageNotification notificationRequestDto) {
    // Message message = Message.builder()
    // .setToken(notificationRequestDto.getTarget())
    // .setNotification(new Notification(notificationRequestDto.getTitle(),
    // notificationRequestDto.getBody()))
    // .putData("content", notificationRequestDto.getTitle())
    // .putData("body", notificationRequestDto.getBody())
    // .build();

    // String response = null;
    // try {
    // response = FirebaseMessaging.getInstance().send(message);
    // } catch (FirebaseMessagingException e) {
    // System.out.println("Fail to send firebase notification"+e);
    // }

    // return response;
    // }
    @RequestMapping(value = "/rechargermoncompte/{id}/{token}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void insertEnchere(HttpServletRequest request, @PathVariable int id, @PathVariable String token) {
        Map<String, Object> map = new HashMap<>();
        CompteClient compte = new CompteClient();
        compte.setClientid(id);
        compte.setMontant(Double.parseDouble(request.getParameter("montant")));
        compte.setEtat(0);
        compte.setActionTransaction(4);
        GestionToken tok = new GestionToken();
        System.out.println("TOKEN : " + token);
        try {
            Claims cl = tok.testTokenClaims(token);
            client.rechargerMonCompte(compte);
            map.put("message", "Rechargment du compte avec succes");
            map.put("Status", "Succes");
        } catch (Exception e) {
            map.put("Erreur", "Erreur");
        }
    }

    @RequestMapping(value = "/listeMesEncheres/{id}/{token}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> listeMesEncheres(HttpServletRequest request, @PathVariable int id,
            @PathVariable String token) {
        Map<String, Object> map = new HashMap<>();
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(token);
            // map.put("Status","Succes");
            map.put("mesEncheres", mesEncheresRepository.getMesEncheres(id));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("Erreur", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/infoEnchere/{id}/{token}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> infoEnchere(HttpServletRequest request, @PathVariable int id,
            @PathVariable String token) {
        Map<String, Object> map = new HashMap<>();
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(token);
            // map.put("Status","Succes");
            map.put("infoEnchere", mesEncheresRepository.getInfoEnchere(id));
            System.out.println("Token : " + token);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("Erreur", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/getProduitByCategorie/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> getProduitByCategorie(HttpServletRequest request, @PathVariable int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("produit", produitRepository.getProduitByCategorie(id));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("Erreur", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/getClientById/{id}/{token}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> getClientById(HttpServletRequest request, @PathVariable int id,
            @PathVariable String token) {
        Map<String, Object> map = new HashMap<>();
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(token);
            map.put("client", client.getClientById(id));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("Erreur", e.getMessage());
        }
        return map;
    }

}
