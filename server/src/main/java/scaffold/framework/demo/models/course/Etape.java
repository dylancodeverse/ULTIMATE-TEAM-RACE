package scaffold.framework.demo.models.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import orm.DynamicORM;
import orm.annotations.Id;
import orm.annotations.Ignore;
import scaffold.framework.demo.config.opencsvconfig.DoubleConverter;
import scaffold.framework.demo.config.opencsvconfig.IntegerConverter;
import scaffold.framework.demo.config.opencsvconfig.StringConvert;
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
    @CsvBindByName(column = "nb coureur")
    String nbcoureur;
    @Ignore
    @CsvBindByName(column = "rang")
    String rang;
    @Ignore
    @CsvBindByName(column = "date depart")
    String datedepart;
    @Ignore
    @CsvBindByName(column = "heure depart")
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
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "insert into etape (nom,longueurkm,nbcoureur,rangetape,depart) values(?,?,?,?,?) on conflict do nothing");
            preparedStatement.setString(1, etape.getNom());
            preparedStatement.setDouble(2, etape.getLongueurKM());
            preparedStatement.setInt(3, etape.getNbCoureur());
            preparedStatement.setInt(4, etape.getRangEtape());
            preparedStatement.setTimestamp(5, etape.getDepart());

            preparedStatement.executeUpdate();
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
        Nom = StringConvert.convert(nom) ;
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
