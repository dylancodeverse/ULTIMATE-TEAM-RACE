package scaffold.framework.demo.models;

import orm.DynamicORM;
import orm.annotations.Id;

public class AppUser extends DynamicORM<AppUser> {

    @Id
    String ID;
    String login;
    String pswd;

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
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
