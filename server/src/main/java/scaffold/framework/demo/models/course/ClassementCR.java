package scaffold.framework.demo.models.course;

import org.postgresql.util.PGInterval;

import orm.DynamicORM;

public class ClassementCR extends DynamicORM<ClassementCR> {
    Integer rang;
    String id;
    PGInterval depart;
    PGInterval arrivee;
    String etapenom;
    String coureurnom;
    String equipe;
    Integer points;

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

    public PGInterval getDepart() {
        return depart;
    }

    public void setDepart(PGInterval depart) {
        this.depart = depart;
    }

    public PGInterval getArrivee() {
        return arrivee;
    }

    public void setArrivee(PGInterval arrivee) {
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
