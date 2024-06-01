package scaffold.framework.demo.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import scaffold.framework.demo.config.springAuth.annotations.Auth;
import scaffold.framework.demo.config.springAuth.rules.RulesConf;
import scaffold.framework.demo.models.course.ClassementCR;
import scaffold.framework.demo.models.course.Etape;

@Controller
@RequestMapping("/home")
public class CTHome {

    @Autowired
    DataSource dataSource;

    @GetMapping("/home")
    @Auth(classSource = RulesConf.class, rule = "connected")
    public String getMethodName(Model model) throws SQLException, Exception {
        model.addAttribute("etapes", new Etape().select(dataSource.getConnection(), false));
        return "pages/home/etape";
    }

    @GetMapping("/classementCR")
    public String getPage(Model model) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            model.addAttribute("classement", new ClassementCR().select(connection,false));
            return "pages/home/classementCR";
        } finally{
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

}
