package scaffold.framework.demo.models.course;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import orm.DynamicORM;
import orm.annotations.Id;
import orm.annotations.Ignore;
import scaffold.framework.demo.config.opencsvconfig.DoubleConverter;
import scaffold.framework.demo.config.opencsvconfig.IntegerConverter;
import scaffold.framework.demo.config.opencsvconfig.TimestampConverter;

public class Etape extends DynamicORM<Etape> {

    @Id
    String ID;
    String Nom;
    Double LongueurKM;
    Integer nbCoureur;
    Integer rangEtape;
    Timestamp depart;

    //
    @Ignore
    @CsvBindByName(column = "etape")
    String etape;
    @Ignore
    @CsvBindByName(column = "longueur")
    String longueur;
    @Ignore
    @CsvBindByName(column = "nbcoureur")
    String nbcoureur;
    @Ignore
    @CsvBindByName(column = "rang")
    String rang;
    @Ignore
    @CsvBindByName(column = "datedepart")
    String datedepart;
    @Ignore
    @CsvBindByName(column = "heuredepart")
    String heuredepart;

    public void setAll() {
        setNom(getEtape());
        setLongueurKM(DoubleConverter.convert(getLongueur()));
        setNbCoureur(IntegerConverter.convert(getNbcoureur()));
        setRangEtape(IntegerConverter.convert(getRang()));
        setDepart(TimestampConverter.convert(getDatedepart(), getHeuredepart()));
    }

    public static void insertAll(Connection connection, List<Etape> importedDataList) throws Exception {
        for (Etape etape : importedDataList) {
            etape.setAll();
            etape.insert(connection, true);
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Double getLongueurKM() {
        return LongueurKM;
    }

    public void setLongueurKM(Double longueurKM) {
        LongueurKM = longueurKM;
    }

    public Integer getNbCoureur() {
        return nbCoureur;
    }

    public void setNbCoureur(Integer nbCoureur) {
        this.nbCoureur = nbCoureur;
    }

    public Integer getRangEtape() {
        return rangEtape;
    }

    public void setRangEtape(Integer rangEtape) {
        this.rangEtape = rangEtape;
    }

    public Timestamp getDepart() {
        return depart;
    }

    public void setDepart(Timestamp depart) {
        this.depart = depart;
    }

    public String getEtape() {
        return etape;
    }

    public void setEtape(String etape) {
        this.etape = etape;
    }

    public String getLongueur() {
        return longueur;
    }

    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    public String getNbcoureur() {
        return nbcoureur;
    }

    public void setNbcoureur(String nbcoureur) {
        this.nbcoureur = nbcoureur;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(String datedepart) {
        this.datedepart = datedepart;
    }

    public String getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(String heuredepart) {
        this.heuredepart = heuredepart;
    }

}
