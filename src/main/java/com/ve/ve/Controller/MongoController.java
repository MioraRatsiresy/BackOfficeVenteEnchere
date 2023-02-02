package com.ve.ve.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ve.ve.Gestiontoken.GestionToken;
import com.ve.ve.Model.MiserEnchere;
import com.ve.ve.Model.PhotoEnchere;
import com.ve.ve.Model.UserPhoto;
import com.ve.ve.Repository.EnchereRepository;
import com.ve.ve.Repository.MiserEnchereRepository;
import com.ve.ve.Repository.PhotoEnchereRepository;

import io.jsonwebtoken.Claims;

@Controller
public class MongoController {
    @Autowired
    private PhotoEnchereRepository photo;

    @Autowired
    private EnchereRepository enchere;

    @RequestMapping(value = "/insertPhoto/{id}/{token}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> insertPhoto(@RequestBody UserPhoto[] photos, @PathVariable int id,
            HttpServletRequest request,
            @PathVariable String token) {
        System.out.println("Insert photo");
        Map<String, Object> map = new HashMap<>();
        PhotoEnchere photoenchere = new PhotoEnchere();
        System.out.println("Taille :" + photos.length);
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(token);
            photoenchere.setIdEnchere(id);
            for (int i = 0; i < photos.length; i++) {
                photoenchere.setPhoto(photos[i].getBase64().split("data:image/png;base64,")[1]);
                photo.insert(photoenchere);
            }
            System.out.println("Token : " + token);
            System.out.println("Image en base 64 lol : " + photo);
            System.out.println("ok");
            map.put("Status", "Insertion avec succes");
        } catch (Exception e) {
            map.put("Erreur", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/miser/{id}/{token}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> miser(@PathVariable int id, HttpServletRequest request, @PathVariable String token) {
        Map<String, Object> map = new HashMap<>();
        MiserEnchere me = new MiserEnchere();
        me.setIdEnchere(id);
        me.setIdclient(Integer.parseInt(request.getParameter("idclient")));
        me.setMontant(Double.parseDouble(request.getParameter("montant")));
        GestionToken tok = new GestionToken();
        try {
            Claims cl = tok.testTokenClaims(token);
            if (enchere.verifyCompte(me)) {
                enchere.rencherir(me);
                map.put("Status", "Insertion avec succes");
            } else {
                map.put("Compte", "Solde insuffisant pour cette transaction");
            }
        } catch (Exception e) {
            map.put("Erreur", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/getPhotoEnchere/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public List<PhotoEnchere> getPhoto(@PathVariable int id) {
        return photo.findByIdEnchere(id);
    }

}
