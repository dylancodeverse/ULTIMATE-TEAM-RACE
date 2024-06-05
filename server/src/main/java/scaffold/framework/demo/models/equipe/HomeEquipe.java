package scaffold.framework.demo.models.equipe;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

public class HomeEquipe {

    String etapenom;
    String etapeId;
    Timestamp departEtape;
    ArrayList<String> coureurs;
    ArrayList<String> chrono;

    public HomeEquipe() {
        coureurs = new ArrayList<>();
        chrono = new ArrayList<>();
    }

    public static HomeEquipe[] getAllByEquipeID(Connection connection, String equipeID) throws Exception {
        CLASSEMENTPARETAPEAVECCHRONO[] cls = new CLASSEMENTPARETAPEAVECCHRONO().selectWhere(connection, false,
                "equipeid='" + equipeID + "'");
        if (cls.length == 0) {
            return new HomeEquipe[0];
        }
        ArrayList<HomeEquipe> homeEquipes = new ArrayList<HomeEquipe>();

        String etapetemp = cls[0].getEtapenom();
        HomeEquipe homeEquipe = new HomeEquipe();
        homeEquipe.setEtapenom(etapetemp);
        homeEquipe.setEtapeId(cls[0].getEtapeid());
        homeEquipe.setDepartEtape(cls[0].getDepart());
        for (CLASSEMENTPARETAPEAVECCHRONO classementparetapeavecchrono : cls) {
            if (!etapetemp.equals(classementparetapeavecchrono.getEtapenom())) {
                homeEquipe = new HomeEquipe();
                etapetemp = classementparetapeavecchrono.getEtapenom();
                homeEquipe.setEtapenom(etapetemp);
                homeEquipe.setEtapeId(classementparetapeavecchrono.getEtapeid());
                homeEquipe.setDepartEtape(classementparetapeavecchrono.getDepart());
                homeEquipes.add(homeEquipe);
            }
            homeEquipe.setChrono(classementparetapeavecchrono.getChrono().toString().split("days")[1]);
            homeEquipe.setCoureurs(classementparetapeavecchrono.getCoureurnom());
        }
        return homeEquipes.toArray(new HomeEquipe[homeEquipes.size()]);
    }

    private void setCoureurs(String coureurnom) {
        coureurs.add(coureurnom);
    }

    private void setChrono(String string) {
        chrono.add(string);
    }

    public String getEtapenom() {
        return etapenom;
    }

    public void setEtapenom(String etapenom) {
        this.etapenom = etapenom;
    }

    public ArrayList<String> getCoureurs() {
        return coureurs;
    }

    public void setCoureurs(ArrayList<String> coureurs) {
        this.coureurs = coureurs;
    }

    public ArrayList<String> getChrono() {
        return chrono;
    }

    public void setChrono(ArrayList<String> chrono) {
        this.chrono = chrono;
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

}
