package scaffold.framework.demo.models.equipe;

import orm.DynamicORM;
import orm.annotations.Id;

public class Equipe extends DynamicORM<Equipe> {

    @Id
    String ID;
    String nom;
    String login;
    String pswd;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

}
