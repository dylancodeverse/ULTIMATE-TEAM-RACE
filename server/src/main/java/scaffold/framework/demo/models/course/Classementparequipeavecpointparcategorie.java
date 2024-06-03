package scaffold.framework.demo.models.course;

import java.math.BigDecimal;

import orm.DynamicORM;

public class Classementparequipeavecpointparcategorie extends DynamicORM<Classementparequipeavecpointparcategorie> {
    Long rank;
    String equipe;
    String categorie;
    Long point;

    public void setPoint(BigDecimal big) {
        setPoint(Long.parseLong(big.toPlainString()));
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = (point);
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = (rank);
    }
}
