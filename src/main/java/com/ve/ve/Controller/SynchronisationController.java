package com.vehicule.vehicule.Controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vehicule.vehicule.Model.Centrifugation;
import com.vehicule.vehicule.Model.Explosion;
import com.vehicule.vehicule.Model.PointCarriere;
import com.vehicule.vehicule.Model.RequeteEnvoye;
import com.vehicule.vehicule.Model.RequeteRecu;
import com.vehicule.vehicule.Model.RequeteRecuExecute;
import com.vehicule.vehicule.Model.Totoana;
import com.vehicule.vehicule.Repository.CarriereMinierRepository;
import com.vehicule.vehicule.Repository.CentrifugationRepository;
import com.vehicule.vehicule.Repository.ExplosionRepository;
import com.vehicule.vehicule.Repository.PointCarriereRepository;
import com.vehicule.vehicule.Repository.SynchronisationRepository;
import com.vehicule.vehicule.Repository.TotoanaRepository;

@Controller
public class SynchronisationController {
    @Autowired
    private SynchronisationRepository sync;
    @Autowired
    private PointCarriereRepository point;

    @Autowired
    private CarriereMinierRepository carriere;

    @Autowired
    private ExplosionRepository explosion;
    @Autowired
    private CentrifugationRepository centrifugation;
    @Autowired
    private TotoanaRepository totoana;

    // insert Point carriere
    // @RequestMapping(value = "/liste/pointcarriere/{id}", method =
    // RequestMethod.GET, produces = "application/json")
    // @ResponseBody
    @GetMapping("/liste/pointcarriere")
    // @CrossOrigin
    public String listepointcarriere(Model model) {
        // Map<String, Object> map = new HashMap<>();
        // map.put("data", point.getListePointCarriere(id));
        model.addAttribute("listepointcarriere", point.getListePointCarriere());
        return "liste";
    }

    @GetMapping("/detail/{id}")
    // @CrossOrigin
    public String detail(@PathVariable String id, Model model) {
        model.addAttribute("explosion", explosion.getExplosion(id));
        model.addAttribute("totoana", totoana.getTotoana(id));
        model.addAttribute("centrifugation", centrifugation.getCentrifugation(id));
        model.addAttribute("id", id);
        return "detail";
    }

    @PostMapping("/insert/pointcarriere")
    // @CrossOrigin
    public String insertPointCarriere(Model model, HttpServletRequest request) {
        PointCarriere pt = new PointCarriere();
        pt.setNompoint(request.getParameter("nompoint"));
        pt.setXposition(Integer.parseInt(request.getParameter("xposition")));
        pt.setYposition(Integer.parseInt(request.getParameter("yposition")));
        point.insertPointCarriere(pt);
        // Map<String, Object> map = new HashMap<>();
        // map.put("data", point.getListePointCarriere(id));
        model.addAttribute("listepointcarriere", point.getListePointCarriere());
        return "liste";
    }

    @PostMapping("/insert/explosion/{id}")
    // @CrossOrigin
    public String insertExplosion(Model model, HttpServletRequest request, @PathVariable String id) {
        Explosion exp = new Explosion();
        System.out.println(request.getParameter("dateexplosion"));
        exp.setDateexplosion(request.getParameter("dateexplosion"));
        exp.setIdpoint(id);
        exp.setQuantiteVato(Double.parseDouble(request.getParameter("quantiteVato")));
        explosion.insertExplosion(exp);
        // Map<String, Object> map = new HashMap<>();
        // map.put("data", point.getListePointCarriere(id));
        return "redirect:/detail/" + id;
    }

    @PostMapping("/insert/toto/{id}")
    // @CrossOrigin
    public String insertToto(Model model, HttpServletRequest request, @PathVariable String id) {
        Totoana toto = new Totoana();
        toto.setQuantiteTotoana(Double.parseDouble(request.getParameter("quantiteTotoana")));
        toto.setDateheuredebut(request.getParameter("dateheuredebut").split("T")[0] + " "
                + request.getParameter("dateheuredebut").split("T")[1]);
        toto.setDateheurefin(request.getParameter("dateheurefin").split("T")[0] + " "
                + request.getParameter("dateheurefin").split("T")[1]);
        toto.setIdpoint(id);
        totoana.insertTotoana(toto);
        // Map<String, Object> map = new HashMap<>();
        // map.put("data", point.getListePointCarriere(id));
        return "redirect:/detail/" + id;
    }

    @PostMapping("/insert/centrifugation/{id}")
    // @CrossOrigin
    public String insertCentrifugation(Model model, HttpServletRequest request, @PathVariable String id) {
        Centrifugation centrifug = new Centrifugation();
        centrifug.setDateheuredebut(request.getParameter("dateheuredebut").split("T")[0] + " "
                + request.getParameter("dateheuredebut").split("T")[1]);
        centrifug.setDateheurefin(request.getParameter("dateheurefin").split("T")[0] + " "
                + request.getParameter("dateheurefin").split("T")[1]);
        centrifug.setIdpoint(id);
        centrifug.setQuantiteVato(Double.parseDouble(request.getParameter("quantiteVato")));
        centrifug.setQuantiteVolamena(Double.parseDouble(request.getParameter("quantiteVolamena")));
        centrifugation.insertCentrifugation(centrifug);
        // Map<String, Object> map = new HashMap<>();
        // map.put("data", point.getListePointCarriere(id));
        return "redirect:/detail/" + id;
    }

    @RequestMapping(value = "/synchroniser", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> synchroniser() {
        System.out.println("Zety");
        Map<String, Object> map = new HashMap<>();
        map.put("data", sync.getRequeteNonEnvoye());
        return map;
    }

    @RequestMapping(value = "/recevoir/donnee", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> recevoirdonne(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("Recevoir donnee from postgresql   "+(request.getParameter("requete")));
        // map.put("data",sync.getRequeteNonEnvoye());
        System.out.println("Requete"+request.getParameter("requete"));
        RequeteRecu recu = new RequeteRecu();
        recu.setRequest(request.getParameter("requete"));
        recu.setServeur(Integer.parseInt(request.getParameter("serveur")));
        sync.insertRequeteRecu(recu);
        return map;
    }

    @RequestMapping(value = "/insertRequeteEnvoye/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> insertRequeteEnvoye(@PathVariable int id) {
        Map<String, Object> map = new HashMap<>();
        RequeteEnvoye env = new RequeteEnvoye();
        env.setIdrequestnotsend(id);
        sync.insertRequeteEnvoye(env);
        return map;
    }

    @RequestMapping(value = "/updateRequeteNonEnvoye/{id}/{serveur}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> updateRequeteNonEnvoye(@PathVariable int id,@PathVariable int serveur) {
        Map<String, Object> map = new HashMap<>();
        sync.updateRequeteNonEnvoye(id, serveur);
        return map;
    }

    @RequestMapping(value = "/insertrequeterecu", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> insertrequeterecu() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<RequeteRecu> recu=sync.getRequeteRecu();
        for(int i=0;i<recu.size();i++){
            sync.insertrequete(recu.get(i).getRequest().replace("|", "'"));
            RequeteRecuExecute rc=new RequeteRecuExecute();
            rc.setRecu(recu.get(i).getId());
            sync.insertRequeteRecuExecute(rc);
        }
        return map;
    }
    @RequestMapping(value = "/getIdPointSqlite", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> getIdPointSqlite(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("data",point.getIdPointSqlite(Integer.parseInt(request.getParameter("id"))));
        return map;
    }

}
