package scaffold.framework.demo.models.course;

import java.sql.Timestamp;

import orm.DynamicORM;

public class ClassementCRparetape extends DynamicORM<ClassementCRparetape> {
    Long rang;
    String id;
    Timestamp arrivee;
    String etapenom;
    String coureurnom;
    String equipe;
    Long points;

    public Long getRang() {
        return rang;
    }

    public void setRang(Long rang) {
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

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
