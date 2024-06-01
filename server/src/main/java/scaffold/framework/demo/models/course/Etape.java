package scaffold.framework.demo.models.course;

import orm.DynamicORM;
import orm.annotations.Id;

public class Etape extends DynamicORM<Etape> {

    @Id
    String ID;
    String Nom;
    Double LongueurKM;
    Integer nbCoureur;
    Integer rangEtape;

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

}
