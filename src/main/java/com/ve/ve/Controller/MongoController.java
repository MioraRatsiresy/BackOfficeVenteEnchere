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

import com.ve.ve.Model.MiserEnchere;
import com.ve.ve.Repository.MiserEnchereRepository;
import com.ve.ve.Repository.PhotoEnchereRepository;


@Controller
public class MongoController {
    @Autowired
    private PhotoEnchereRepository photo;

    @Autowired
    private MiserEnchereRepository miser;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String,Object> listeProfil() {
        Map<String,Object> map=new HashMap<>();
        map.put("photo",photo.findAll());
        return map;
    }

    @RequestMapping(value = "/miser/{id}", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public String miser(@PathVariable int id,HttpServletRequest request) {
        MiserEnchere me=new MiserEnchere();
        me.setIdEnchere(id);
        me.setIdclient(Integer.parseInt(request.getParameter("idclient")));
        me.setMontant(Double.parseDouble(request.getParameter("montant")));
        me.setDateheure(request.getParameter("dateheure"));
        miser.save(me);
        return "Insertion avec succès";
    }


 

}
