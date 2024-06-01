package scaffold.framework.demo.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import scaffold.framework.demo.models.equipe.Equipe;

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
            return "redirect:/home/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/equipe/login";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

}
