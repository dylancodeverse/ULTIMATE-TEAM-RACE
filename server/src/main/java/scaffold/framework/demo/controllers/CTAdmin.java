package scaffold.framework.demo.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import scaffold.framework.demo.config.springAuth.annotations.Auth;
import scaffold.framework.demo.config.springAuth.rules.RulesConf;
import scaffold.framework.demo.models.AppUser;
import scaffold.framework.demo.models.course.ClassementEQ;
import scaffold.framework.demo.models.course.Classementparequipeavecpointparcategorie;
import scaffold.framework.demo.models.course.CompletResultatEtape;
import scaffold.framework.demo.models.course.ResultatEtape;
import scaffold.framework.demo.models.course.Etape;
import scaffold.framework.demo.models.imports.Point;
import scaffold.framework.demo.models.imports.ResultatCSV;

@Controller
@RequestMapping("/admin")
public class CTAdmin {

    @Value("${tempFile}")
    String tempFile;

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
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String getPageAffectationTemps(Model model) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            CompletResultatEtape[] completResultatEtapes = new CompletResultatEtape()
                    .selectWhere(connection, false, "arrivee is null");
            model.addAttribute("completResultatEtapes", completResultatEtapes);
            return "/pages/admin/affectation";
        } finally {
            if (connection.isClosed()) {
                connection.close();
            }
        }
    }

    @PostMapping("/affectation")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String affecter(String ID, String arrivee, Date date) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            ResultatEtape resultatEtape = new ResultatEtape();
            resultatEtape.setID(ID);
            resultatEtape.setArrivee(date.toString() + " " + arrivee);
            resultatEtape.updateById(connection, false);
            return "redirect:/admin/affectation";
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @GetMapping("/reinit")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String getMethodName() throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            connection.createStatement().executeQuery("select reset_database()");
        } finally {
            connection.close();
        }
        return "redirect:/home/home";
    }

    @GetMapping("/importetaperesultats")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String pageImport1() {
        return "pages/admin/importationEtapeResultat";
    }

    @GetMapping("/importpoint")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String pageImport2() {
        return "pages/admin/importationPoint";
    }

    @PostMapping("/importetaperesultats")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String importER(@RequestParam("etape") MultipartFile etape, @RequestParam("resultat") MultipartFile resultat,
            char separateur) throws Exception {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            // import etape

            if (!etape.isEmpty()) {
                try (
                        Reader reader = new BufferedReader(
                                new InputStreamReader(etape.getInputStream()))) {
                    CsvToBean<Etape> csvToBean = new CsvToBeanBuilder<Etape>(
                            reader)
                            .withType(Etape.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .withSeparator(separateur)
                            .build();
                    List<Etape> importedDataList = csvToBean.parse();
                    Etape.insertAll(connection, importedDataList);
                    System.out.println("vita etape");
                }
                // import resultat
                if (!resultat.isEmpty()) {
                    try (
                            Reader reader = new BufferedReader(
                                    new InputStreamReader(resultat.getInputStream()))) {
                        CsvToBean<ResultatCSV> csvToBean = new CsvToBeanBuilder<ResultatCSV>(
                                reader)
                                .withType(ResultatCSV.class)
                                .withIgnoreLeadingWhiteSpace(true)
                                .withSeparator(separateur)
                                .build();
                        List<ResultatCSV> importedDataList = csvToBean.parse();
                        ResultatCSV.insertAll(connection, importedDataList);
                        ResultatCSV.insertAllPeripherie(connection);
                    }

                }
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (!connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        }

        return "redirect:/admin/importetaperesultats";
    }

    @PostMapping("/importpoint")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String importPT(@RequestParam("point") MultipartFile point, char separateur) throws Exception {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            // import etape

            if (!point.isEmpty()) {
                try (
                        Reader reader = new BufferedReader(
                                new InputStreamReader(point.getInputStream()))) {
                    CsvToBean<Point> csvToBean = new CsvToBeanBuilder<Point>(
                            reader)
                            .withType(Point.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .withSeparator(separateur)
                            .build();
                    List<Point> importedDataList = csvToBean.parse();
                    Point.insertAll(connection, importedDataList);
                }
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (!connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        }
        return "redirect:/admin/importpoint";
    }

    @GetMapping("/generateCategorie")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public String generateCategorie() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            java.sql.Statement s = connection.createStatement();
            s.executeUpdate("delete from coureurcategorie");

            s.executeUpdate("delete from categorie;");
            s.executeUpdate(
                    "INSERT INTO Categorie (ID,NOM) SELECT Categorie, Categorie FROM categoriecomplet where categorie is not null on CONFLICT do NOTHING;");
            s.executeUpdate(
                    "insert into coureurcategorie(categorie,coureur) select categorie,id   from categoriecomplet where categorie is not null;");
        } finally {
            connection.commit();
            connection.close();
        }
        return "redirect:/home/home";
    }

    @GetMapping("/classementGeneralgeneratePDF")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public ResponseEntity<byte[]> classementGeneralgeneratePDF() throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            byte[] pdfContent = new ClassementEQ().generatePDFSelonPlace(connection, tempFile, "rang=1");
            // Créer les en-têtes de la réponse
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Nom du fichier PDF téléchargé
            headers.setContentDispositionFormData("filename", "example.pdf");
            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @GetMapping("/classementParCategoriegeneratePDF")
    @Auth(classSource = RulesConf.class, rule = "isAdmin")
    public ResponseEntity<byte[]> classementParCategorie(String categorie) throws Exception {
        Connection connection = dataSource.getConnection();
        try {
            byte[] pdfContent = new Classementparequipeavecpointparcategorie().generatePDFSelonPlace(connection,
                    tempFile, "rang=1 and categorie='" + categorie.trim() + "'");
            // Créer les en-têtes de la réponse
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Nom du fichier PDF téléchargé
            headers.setContentDispositionFormData("filename", "example.pdf");
            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } finally {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

}
