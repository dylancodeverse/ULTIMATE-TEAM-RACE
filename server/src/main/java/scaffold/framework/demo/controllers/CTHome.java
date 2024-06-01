package scaffold.framework.demo.controllers;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import scaffold.framework.demo.models.course.Etape;

@Controller
@RequestMapping("/home")
public class CTHome {

    @Autowired
    DataSource dataSource;

    @GetMapping("/home")
    public String getMethodName(Model model) throws SQLException, Exception {
        model.addAttribute("etapes", new Etape().select(dataSource.getConnection(), false));
        return "pages/home/etape";
    }

}
