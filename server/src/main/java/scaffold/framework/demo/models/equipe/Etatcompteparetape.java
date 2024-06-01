package scaffold.framework.demo.models.equipe;

import orm.DynamicORM;

public class Etatcompteparetape extends DynamicORM<Etatcompteparetape> {
    Boolean estcomplet;
    String etape;
    String equipe;

    public Boolean getEstcomplet() {
        return estcomplet;
    }

    public void setEstcomplet(Boolean estcomplet) {
        this.estcomplet = estcomplet;
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
}
