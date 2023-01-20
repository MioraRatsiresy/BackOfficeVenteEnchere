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

import io.jsonwebtoken.Claims;

@Controller
public class VenteEchereMobile {
    @Autowired
    private AdminLoginRepository user;

    @Autowired 
    private CategorieRepository categorie;

    @Autowired
    private ClientRepository client;

    // @PostMapping("/token")
    // public String sendPnsToDevice(HttpServletRequest request) {
    //     MessageNotification msg=new MessageNotification();
    //     msg.setBody(request.getParameter("body"));
    //     msg.setTarget(request.getParameter("target"));
    //     msg.setTitle(request.getParameter("title"));
    //     return this.sendPnsToDevice(msg);
    // }
    // public String sendPnsToDevice(MessageNotification notificationRequestDto) {
    //     Message message = Message.builder()
    //             .setToken(notificationRequestDto.getTarget())
    //             .setNotification(new Notification(notificationRequestDto.getTitle(), notificationRequestDto.getBody()))
    //             .putData("content", notificationRequestDto.getTitle())
    //             .putData("body", notificationRequestDto.getBody())
    //             .build();

    //     String response = null;
    //     try {
    //         response = FirebaseMessaging.getInstance().send(message);
    //     } catch (FirebaseMessagingException e) {
    //         System.out.println("Fail to send firebase notification"+e);
    //     }

    //     return response;
    // }
    @RequestMapping(value = "/rechargermoncompte/{id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public void insertEnchere(HttpServletRequest request,@PathVariable int id) {
        Map<String,Object> map=new HashMap<>();
        CompteClient compte=new CompteClient();
        compte.setClientid(id);
        compte.setMontant(Double.parseDouble(request.getParameter("montant")));
        compte.setEtat(0);
        compte.setActionTransaction(4);
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(request);
            client.rechargerMonCompte(compte);
            map.put("Status","Rechargment du compte avec succes");
        }
        catch(Exception e){
            map.put("Erreur",e.getMessage());
        }
    }

 

}
