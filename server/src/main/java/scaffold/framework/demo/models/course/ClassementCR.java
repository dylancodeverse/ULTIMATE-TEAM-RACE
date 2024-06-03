package scaffold.framework.demo.models.course;

import orm.DynamicORM;

public class ClassementCR extends DynamicORM<ClassementCR> {
    Long rang;
    String coureurnom;
    String equipe;
    Long points;

    public Long getRang() {
        return rang;
    }

    public void setRang(Long rang) {
        this.rang = rang;
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
