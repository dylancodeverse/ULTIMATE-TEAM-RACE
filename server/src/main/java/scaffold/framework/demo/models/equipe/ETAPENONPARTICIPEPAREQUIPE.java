package scaffold.framework.demo.models.equipe;

import java.sql.Timestamp;

import orm.DynamicORM;

public class ETAPENONPARTICIPEPAREQUIPE extends DynamicORM<ETAPENONPARTICIPEPAREQUIPE> {
    String equipeid;
    String id;
    String nom;
    Double longueurkm;
    Integer nbcoureur;
    Integer rangetape;
    Timestamp depart;

    public String getEquipeid() {
        return equipeid;
    }

    public void setEquipeid(String equipeid) {
        this.equipeid = equipeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Timestamp getDepart() {
        return depart;
    }

    public void setDepart(Timestamp depart) {
        this.depart = depart;
    }

    public Double getLongueurkm() {
        return longueurkm;
    }

    public void setLongueurkm(Double longueurkm) {
        this.longueurkm = longueurkm;
    }

    public Integer getNbcoureur() {
        return nbcoureur;
    }

    public void setNbcoureur(Integer nbcoureur) {
        this.nbcoureur = nbcoureur;
    }

    public Integer getRangetape() {
        return rangetape;
    }

    public void setRangetape(Integer rangetape) {
        this.rangetape = rangetape;
    }
}
