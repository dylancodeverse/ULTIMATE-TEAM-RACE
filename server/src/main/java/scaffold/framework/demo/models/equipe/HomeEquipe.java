package scaffold.framework.demo.models.equipe;

import java.sql.Connection;
import java.sql.Timestamp;

import scaffold.framework.demo.models.course.Etape;

public class HomeEquipe {

    String etapenom;
    String etapeId;
    Timestamp departEtape;
    CLASSEMENTPARETAPEAVECCHRONO[] participants;

    public static HomeEquipe[] getAllByEquipeID(Connection connection, String equipeID) throws Exception {
        Etape[] etapes = new Etape().select(connection, true);
        HomeEquipe[] homeEquipes = new HomeEquipe[etapes.length];
        for (int i = 0; i < homeEquipes.length; i++) {
            homeEquipes[i] = new HomeEquipe();
            homeEquipes[i].setEtapenom(etapes[i].getNom());
            homeEquipes[i].setEtapeId(etapes[i].getID());

            homeEquipes[i].setDepartEtape(etapes[i].getDepart());

            homeEquipes[i].setParticipants(new CLASSEMENTPARETAPEAVECCHRONO().selectWhere(connection, true,
                    "equipeid='" + equipeID + "' and etapeid='" + etapes[i].getID() + "'"));
        }
        return homeEquipes;

    }

    public String getEtapenom() {
        return etapenom;
    }

    public void setEtapenom(String etapenom) {
        this.etapenom = etapenom;
    }

    public String getEtapeId() {
        return etapeId;
    }

    public void setEtapeId(String etapeId) {
        this.etapeId = etapeId;
    }

    public Timestamp getDepartEtape() {
        return departEtape;
    }

    public void setDepartEtape(Timestamp departEtape) {
        this.departEtape = departEtape;
    }

    public CLASSEMENTPARETAPEAVECCHRONO[] getParticipants() {
        return participants;
    }

    public void setParticipants(CLASSEMENTPARETAPEAVECCHRONO[] participants) {
        this.participants = participants;
    }
}
