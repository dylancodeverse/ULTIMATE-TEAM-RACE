package scaffold.framework.demo.models.course;

import orm.DynamicORM;

public class ClassementEQ extends DynamicORM<ClassementEQ> {
    Long rang;
    String equipe;
    Integer points;

    public Long getRang() {
        return rang;
    }

    public void setRang(Long rang) {
        this.rang = rang;
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
