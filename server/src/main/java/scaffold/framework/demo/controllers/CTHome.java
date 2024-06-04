package scaffold.framework.demo.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import scaffold.framework.demo.config.springAuth.annotations.Auth;
import scaffold.framework.demo.config.springAuth.rules.RulesConf;
import scaffold.framework.demo.models.course.ClassementCR;
import scaffold.framework.demo.models.course.ClassementCRparetape;
import scaffold.framework.demo.models.course.ClassementEQ;
import scaffold.framework.demo.models.course.Classementparequipeavecpointparcategorie;
import scaffold.framework.demo.models.course.Classementparequipetous;
import scaffold.framework.demo.models.course.Etape;

import io.micrometer.common.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getPageCR(Model model) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            model.addAttribute("classements", new ClassementCR().select(connection, false));
            return "pages/home/classementCR";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @GetMapping("/classementCR/{coureur}")
    public String getMethodName(@PathVariable String coureur, Model model) throws Exception {
        Connection connection = dataSource.getConnection();
        try {

            model.addAttribute("details",
                    new ClassementCRparetape().selectWhere(connection, false, "coureurnom='" + coureur + "'"));
            return "pages/home/classementCRDetail";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @GetMapping("/classementEQ")
    public String getPageEQ(Model model) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            ClassementEQ[] cls = new ClassementEQ().select(connection, false);
            model.addAttribute("classements", cls);
            model.addAttribute("chart", ClassementEQ.getJSONPieChartInformation(cls));
            return "pages/home/classementEQ";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }

    }

    @GetMapping("/classementParCategorie")
    public String getMethodName(Model model, @Nullable String categorie) throws Exception {
        Connection connection = dataSource.getConnection();

        Classementparequipeavecpointparcategorie[] cls = null;
        if (categorie == null) {
            cls = new Classementparequipetous().select(connection, false);

        } else {
            cls = new Classementparequipeavecpointparcategorie().selectWhere(connection, false,
                    "categorie='" + categorie + "'");
        }
        model.addAttribute("classement", cls);
        model.addAttribute("categorie", categorie);
        model.addAttribute("chart", Classementparequipeavecpointparcategorie.getJSONPieChartInformation(cls));
        return "pages/home/classementParCategorie";
    }

}
