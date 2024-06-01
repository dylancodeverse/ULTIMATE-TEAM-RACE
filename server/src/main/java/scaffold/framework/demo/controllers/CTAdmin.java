package scaffold.framework.demo.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import scaffold.framework.demo.models.AppUser;
import scaffold.framework.demo.models.course.CompletResultatEtape;
import scaffold.framework.demo.models.course.ResultatEtape;

@Controller
@RequestMapping("/admin")
public class CTAdmin {

    @Autowired
    DataSource dataSource;

    @GetMapping("/login")
    public String loginPage() {
        return "pages/admin/login";
    }

    @PostMapping("/login")
    public String loginCheck(Model model, String login, String pswd, HttpServletRequest servlet) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            AppUser appUser = new AppUser();
            appUser.setLogin(login);
            appUser.setPswd(pswd);
            // efa voahidy ho azy ato ilay connection
            AppUser[] appUsers = appUser.selectWhere(connection, false,
                    " login='" + login + "' and pswd='" + pswd + "'");
            if (appUsers.length == 0) {
                throw new Exception("Information de connection incorrecte");
            }
            HttpSession session = servlet.getSession(true);
            session.setAttribute("USRID", appUsers[0].getID());
            session.setAttribute("ISADMIN", 10);
            return "redirect:/home/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/login";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @GetMapping("/affectation")
    public String getPageAffectationTemps(Model model) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            CompletResultatEtape[] completResultatEtapes = new CompletResultatEtape()
                    .selectWhere(connection, false, "depart is null or arrivee is null");
            model.addAttribute("completResultatEtapes", completResultatEtapes);
            return "/pages/admin/affectation";
        } finally {
            if (connection.isClosed()) {
                connection.close();
            }
        }
    }

    @PostMapping("/affectation")
    public String affecter(String ID, String depart, String arrivee) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            ResultatEtape resultatEtape = new ResultatEtape();
            resultatEtape.setID(ID);
            resultatEtape.setDepart(depart);
            resultatEtape.setArrivee(arrivee);
            resultatEtape.updateById(connection, false);
            return "redirect:/admin/affectation";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

}
