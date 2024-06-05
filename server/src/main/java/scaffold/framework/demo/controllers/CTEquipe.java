package scaffold.framework.demo.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import scaffold.framework.demo.config.springAuth.annotations.Auth;
import scaffold.framework.demo.config.springAuth.rules.RulesConf;
import scaffold.framework.demo.models.course.Etape;
import scaffold.framework.demo.models.course.ResultatEtape;
import scaffold.framework.demo.models.equipe.Coureur;
import scaffold.framework.demo.models.equipe.Equipe;
import scaffold.framework.demo.models.equipe.Etatcompteparetape;
import scaffold.framework.demo.models.equipe.HomeEquipe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/equipe")
public class CTEquipe {
    @Autowired
    DataSource dataSource;

    @GetMapping("/login")
    public String loginPage() {
        return "pages/equipe/login";
    }

    @PostMapping("/login")
    public String login(Model model, String login, String pswd, HttpServletRequest servlet) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            Equipe equipe = new Equipe();
            equipe.setLogin(login);
            equipe.setPswd(pswd);
            Equipe[] equipes = equipe.selectWhere(connection, false, "login='" + login + "' and pswd='" + pswd + "'");
            if (equipes.length == 0) {
                throw new Exception("Information de connection incorrecte");
            }
            HttpSession session = servlet.getSession(true);
            session.setAttribute("USRID", equipes[0].getID());
            session.setAttribute("ISADMIN", 0);
            return "redirect:/equipe/homeEquipe";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/equipe/login";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @GetMapping("/check")
    @Auth(classSource = RulesConf.class, rule = "isEquipe")
    public ResponseEntity<?> getMethodName(HttpServletRequest request, @RequestParam String etapeId) throws SQLException {
        Connection connection = dataSource.getConnection();
        HashMap<String, String> hashMap = new HashMap<>();
        HttpSession session = request.getSession();
        String equipeID = session.getAttribute("USRID").toString();
        try {
            Etatcompteparetape[] etatcompteparetape = new Etatcompteparetape().selectWhere(connection, true,
                    "etape='" + etapeId + "' and equipe='" + equipeID + "'");
            if (etatcompteparetape.length > 0) {
                if (etatcompteparetape[0].getEstcomplet()) {
                    throw new Exception("Deja complet");
                }
            }
        } catch (Exception e) {
            hashMap.put(etapeId, e.getMessage());
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    
        if (hashMap.isEmpty()) {
            System.out.println("empty");
            Map<String, String> response = new HashMap<>();
            response.put("message", "Paiement effectué avec succès !");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(hashMap);
        }
    }
    @GetMapping("/affectation")
    @Auth(classSource = RulesConf.class, rule = "isEquipe")
    public String pageAffectation(Model model, HttpServletRequest request, @Nullable @RequestParam String etapeid)
            throws Exception {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            HttpSession session = request.getSession();
            String equipeID = session.getAttribute("USRID").toString();
            System.out.println(equipeID);
            // prendre tous les coureurs de l'equipe
            Coureur[] coureurs = new Coureur().selectWhere(connection, true, "Equipe='" + equipeID + "'");
            model.addAttribute("coureurs", coureurs);
            // prendre tous les etapes possibles
            Etape[] etapes = new Etape().select(connection, true);

            model.addAttribute("etapes", etapes);
            // manampy selected
            if (etapeid == null) {
                etapeid = "null";
            }
            model.addAttribute("selected", etapeid);

            return "pages/equipe/affectation";

        } finally {
            connection.close();
        }
    }

    @PostMapping("/affectation")
    @Auth(classSource = RulesConf.class, rule = "isEquipe")
    public String affectation(String etape, String coureur, HttpServletRequest request) throws Exception {
        Connection connection = dataSource.getConnection();
        String equipeID = request.getSession().getAttribute("USRID").toString();
        try {
            Etatcompteparetape[] etatcompteparetape = new Etatcompteparetape().selectWhere(connection, true,
                    "etape='" + etape + "' and equipe='" + equipeID + "'");
            if (etatcompteparetape.length > 0) {
                if (etatcompteparetape[0].getEstcomplet()) {
                    throw new Exception("Deja complet");
                }
            }
            ResultatEtape resutlatEtape = new ResultatEtape();
            resutlatEtape.setEtape(etape);
            resutlatEtape.setCoureur(coureur);
            resutlatEtape.insert(connection, false);
            return "redirect:/equipe/affectation";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @GetMapping("/homeEquipe")
    @Auth(classSource = RulesConf.class, rule = "isEquipe")
    public String getPageHome(Model model, HttpServletRequest request) throws Exception {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            HttpSession session = request.getSession();
            String equipeID = session.getAttribute("USRID").toString();
            HomeEquipe[] homeEquipes = HomeEquipe.getAllByEquipeID(connection, equipeID);
            model.addAttribute("homeequipes", homeEquipes);
            return "pages/home/homeEquipe";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }

    }

}
