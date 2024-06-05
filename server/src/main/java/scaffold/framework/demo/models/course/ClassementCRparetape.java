package scaffold.framework.demo.models.course;

import java.sql.Timestamp;

import org.postgresql.util.PGInterval;

import orm.DynamicORM;

public class ClassementCRparetape extends DynamicORM<ClassementCRparetape> {
    Long rang;
    String id;
    Timestamp arrivee;
    String etapenom;
    String coureurnom;
    String equipe;
    Integer point;
    Timestamp arriveeavecpenalite;
    String etapeid;
    String equipeid;
    String coureurid;
    Timestamp depart;
    String genre;
    PGInterval chrono;
    PGInterval tempspenalite;

    public String getTempspenaliteAsSTR() {
        System.out.println("fory");
        try {
            return getTempspenalite().toString().split("mons")[1];

        } catch (Exception e) {
            try {
                return getTempspenalite().toString().split("months")[1];

            } catch (Exception e2) {
                return getTempspenalite().toString();

            }
        }
    }

    public String getChronoAsSTR() {
        System.out.println("fory");
        try {
            return chrono.toString().split("mons")[1];

        } catch (Exception e) {
            try {
                return getTempspenalite().toString().split("months")[1];
            } catch (Exception e2) {
                return chrono.toString();
            }

        }
    }

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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Timestamp getArriveeavecpenalite() {
        return arriveeavecpenalite;
    }

    public void setArriveeavecpenalite(Timestamp arriveeavecpenalite) {
        this.arriveeavecpenalite = arriveeavecpenalite;
    }

    public String getEtapeid() {
        return etapeid;
    }

    public void setEtapeid(String etapeid) {
        this.etapeid = etapeid;
    }

    public String getEquipeid() {
        return equipeid;
    }

    public void setEquipeid(String equipeid) {
        this.equipeid = equipeid;
    }

    public String getCoureurid() {
        return coureurid;
    }

    public void setCoureurid(String coureurid) {
        this.coureurid = coureurid;
    }

    public Timestamp getDepart() {
        return depart;
    }

    public void setDepart(Timestamp depart) {
        this.depart = depart;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public PGInterval getChrono() {
        return chrono;
    }

    public void setChrono(PGInterval chrono) {
        this.chrono = chrono;
    }

    public PGInterval getTempspenalite() {
        return tempspenalite;
    }

    public void setTempspenalite(PGInterval tempspenalite) {
        this.tempspenalite = tempspenalite;
    }
}
