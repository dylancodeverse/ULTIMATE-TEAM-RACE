package scaffold.framework.demo.models.course;

import java.sql.Time;
import jakarta.persistence.Id;
import orm.DynamicORM;

public class Penalite extends DynamicORM<Penalite> {
    @Id
    Integer id;
    String etape;
    String equipe;
    Time tempspenalite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtape() {
        return etape;
    }

    public void setEtape(String etape) {
        this.etape = etape;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public Time getTempspenalite() {
        return tempspenalite;
    }

    public void setTempspenalite(Time tempspenalite) {
        this.tempspenalite = tempspenalite;
    }

    public void setTempspenalite(String timeString) {
        System.out.println(timeString);
        setTempspenalite(Time.valueOf(timeString));
    }
}
