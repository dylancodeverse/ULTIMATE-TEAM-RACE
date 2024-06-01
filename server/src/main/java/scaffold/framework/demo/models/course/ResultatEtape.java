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

    public void setDepart(String depart2) {
        String[] s = depart2.split(":");
        setDepart(new PGInterval(0, 0, 0, 
        Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
    }

    public void setArrivee(String arrivee2) {
        String[] s = arrivee2.split(":");
        setArrivee(new PGInterval(0, 0, 0, Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
    }

}
