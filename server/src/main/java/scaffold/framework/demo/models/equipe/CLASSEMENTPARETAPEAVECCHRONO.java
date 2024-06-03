package scaffold.framework.demo.models.equipe;

import java.sql.Timestamp;

import org.postgresql.util.PGInterval;

import orm.DynamicORM;

public class CLASSEMENTPARETAPEAVECCHRONO extends DynamicORM<CLASSEMENTPARETAPEAVECCHRONO> {
    Integer rang;
    String id;
    Timestamp arrivee;
    String etapenom;
    String coureurnom;
    String equipe;
    String etapeid;
    Integer point;
    Timestamp depart;
    PGInterval chrono;
    String equipeid;

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getArrivee() {
        return arrivee;
    }

    public void setArrivee(Timestamp arrivee) {
        this.arrivee = arrivee;
    }

    public String getEtapenom() {
        return etapenom;
    }

    public void setEtapenom(String etapenom) {
        this.etapenom = etapenom;
    }

    public String getCoureurnom() {
        return coureurnom;
    }

    public void setCoureurnom(String coureurnom) {
        this.coureurnom = coureurnom;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getEtapeid() {
        return etapeid;
    }

    public void setEtapeid(String etapeid) {
        this.etapeid = etapeid;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Timestamp getDepart() {
        return depart;
    }

    public void setDepart(Timestamp depart) {
        this.depart = depart;
    }

    public PGInterval getChrono() {
        return chrono;
    }

    public void setChrono(PGInterval chrono) {
        this.chrono = chrono;
    }

    public String getEquipeid() {
        return equipeid;
    }

    public void setEquipeid(String equipeid) {
        this.equipeid = equipeid;
    }
}
