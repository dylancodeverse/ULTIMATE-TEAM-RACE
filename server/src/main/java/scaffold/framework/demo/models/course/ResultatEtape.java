package scaffold.framework.demo.models.course;

import org.postgresql.util.PGInterval;

import orm.DynamicORM;
import orm.annotations.Id;

public class ResultatEtape extends DynamicORM<ResultatEtape> {

    @Id
    String ID;
    String Etape;
    String Coureur;
    PGInterval Depart;
    PGInterval Arrivee;

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getEtape() {
        return Etape;
    }

    public void setEtape(String etape) {
        Etape = etape;
    }

    public String getCoureur() {
        return Coureur;
    }

    public void setCoureur(String coureur) {
        Coureur = coureur;
    }

    public PGInterval getDepart() {
        return Depart;
    }

    public void setDepart(PGInterval depart) {
        Depart = depart;
    }

    public PGInterval getArrivee() {
        return Arrivee;
    }

    public void setArrivee(PGInterval arrivee) {
        Arrivee = arrivee;
    }

}
