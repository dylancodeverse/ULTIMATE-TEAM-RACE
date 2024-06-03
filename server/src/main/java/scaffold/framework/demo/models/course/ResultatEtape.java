package scaffold.framework.demo.models.course;

import java.sql.Timestamp;
import orm.DynamicORM;
import orm.annotations.Id;

public class ResultatEtape extends DynamicORM<ResultatEtape> {

    @Id
    String ID;
    String Etape;
    String Coureur;
    Timestamp Arrivee;

    public Timestamp getArrivee() {
        return Arrivee;
    }

    public void setArrivee(Timestamp arrivee) {
        Arrivee = arrivee;
    }

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

    public void setArrivee(String arrivee2) {
        setArrivee(Timestamp.valueOf(arrivee2));
    }

}
