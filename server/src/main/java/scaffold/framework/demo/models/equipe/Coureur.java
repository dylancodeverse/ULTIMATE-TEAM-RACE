package scaffold.framework.demo.models.equipe;

import java.sql.Date;

import orm.DynamicORM;

public class Coureur extends DynamicORM<Coureur> {
    String ID;
    String nom;
    Integer numerodossard;
    Integer genre;
    Date dtn;
    String Equipe;

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNumerodossard() {
        return numerodossard;
    }

    public void setNumerodossard(Integer numerodossard) {
        this.numerodossard = numerodossard;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public String getEquipe() {
        return Equipe;
    }

    public void setEquipe(String equipe) {
        Equipe = equipe;
    }

}
