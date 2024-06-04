package scaffold.framework.demo.models.imports;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import orm.DynamicORM;
import orm.annotations.Ignore;
import scaffold.framework.demo.config.opencsvconfig.DateConvert;
import scaffold.framework.demo.config.opencsvconfig.TimestampConverter;

public class ResultatCSV extends DynamicORM<ResultatCSV> {
    @CsvBindByName(column = "etape_rang")

    Integer etape_rang;
    @CsvBindByName(column = "numero dossard")
    String numero_dossard;
    @CsvBindByName(column = "nom")
    String nom;
    @CsvBindByName(column = "genre")
    String genre;

    @CsvBindByName(column = "date naissance")
    @Ignore
    String date_naissanceSTR;
    @CsvBindByName(column = "equipe")

    String equipe;

    @CsvBindByName(column = "arrivee")
    @Ignore
    String arriveeSTR;

    Timestamp arrivee;
    Date date_naissance;

    public void setAll() {
        setDate_naissance(DateConvert.convert(getDate_naissanceSTR()));
        setArrivee(TimestampConverter.convert(getArriveeSTR()));
    }

    public static void insertAll(Connection connection, List<ResultatCSV> importedDataList) throws Exception {
        for (ResultatCSV resultatCSV : importedDataList) {
            resultatCSV.setAll();
            resultatCSV.insert(connection, true);
        }
    }

    public static void insertAllPeripherie(Connection connection) throws Exception {
        String[] sqls = {
                "INSERT INTO EQUIPE(NOM ,LOGIN, PSWD ) SELECT distinct RESULTATCSV.equipe,  RESULTATCSV.equipe, RESULTATCSV.equipe FROM RESULTATCSV on conflict do nothing;",
                "INSERT INTO Coureur ( nom,numerodossard,genre,dtn,Equipe) select distinct RESULTATCSV.nom , RESULTATCSV.numero_dossard , RESULTATCSV.genre , RESULTATCSV.date_naissance , equipe.id from RESULTATCSV join equipe on equipe.nom= RESULTATCSV.equipe on conflict do nothing;",
                "INSERT INTO ResultatEtape (Etape, Coureur, Arrivee)  select distinct etape.ID as etapeid , coureur.id as coureurid, resultatcsv.arrivee from RESULTATCSV  join coureur on RESULTATCSV.nom = coureur.nom  join etape on RESULTATCSV.etape_rang = etape.rangEtape  on conflict do nothing;" };
        for (String string : sqls) {
            Statement statement = connection.createStatement();
            System.out.println(string);
            statement.executeUpdate(string);
            System.out.println("passed");
        }
    }

    public Integer getEtape_rang() {
        return etape_rang;
    }

    public void setEtape_rang(Integer etape_rang) {
        this.etape_rang = etape_rang;
    }

    public String getNumero_dossard() {
        return numero_dossard;
    }

    public void setNumero_dossard(String numero_dossard) {
        this.numero_dossard = numero_dossard;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public Timestamp getArrivee() {
        return arrivee;
    }

    public void setArrivee(Timestamp arrivee) {
        this.arrivee = arrivee;
    }

    public String getDate_naissanceSTR() {
        return date_naissanceSTR;
    }

    public void setDate_naissanceSTR(String date_naissanceSTR) {
        this.date_naissanceSTR = date_naissanceSTR;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getArriveeSTR() {
        return arriveeSTR;
    }

    public void setArriveeSTR(String arriveeSTR) {
        this.arriveeSTR = arriveeSTR;
    }

}
