package scaffold.framework.demo.models.course;

import jakarta.persistence.Id;
import orm.DynamicORM;

public class GetPenaliteAll extends DynamicORM<GetPenaliteAll> {
    @Id
    Integer id;
    String etapes;
    String equipes;
    String temps;

    public String getEtapes() {
        return etapes;
    }
    public void setEtapes(String etapes) {
        this.etapes = etapes;
    }
    public String getEquipes() {
        return equipes;
    }
    public void setEquipes(String equipes) {
        this.equipes = equipes;
    }
    public String getTemps() {
        return temps;
    }
    public void setTemps(String temps) {
        this.temps = temps;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
