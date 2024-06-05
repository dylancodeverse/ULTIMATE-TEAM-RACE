package scaffold.framework.demo.models.course;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Random;

import org.json.JSONArray;

import orm.DynamicORM;
import scaffold.framework.demo.models.pdf.PdfGenerator;

public class Classementparequipeavecpointparcategorie extends DynamicORM<Classementparequipeavecpointparcategorie> {
    Long rank;
    String equipe;
    String categorie;
    Long point;

    public byte[] generatePDFSelonPlace(Connection connection, String fileTemplate, String condition) throws Exception {

        Classementparequipeavecpointparcategorie cls = selectWhere(connection, true, condition)[0];
        return PdfGenerator.generatePdfFromHtml(fileTemplate,
                new String[] { "[Nom du Vainqueur]", "[Date de la Course]" },

                new String[] { cls.getEquipe(), LocalDate.now().toString() });
    }

    public static String[] getJSONPieChartInformation(Classementparequipeavecpointparcategorie[] fs) throws Exception {
        return new String[] { getJSONDATA(fs), getColor(fs), getLabel(fs) };
    }

    public static String getLabel(Classementparequipeavecpointparcategorie[] fs) {
        String[] labels = new String[fs.length];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = fs[i].getEquipe();
        }
        return new JSONArray(labels).toString();
    }

    public static String getJSONDATA(Classementparequipeavecpointparcategorie[] stats) {
        Long[] datas = new Long[stats.length];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = stats[i].getPoint();
        }
        return new JSONArray(datas).toString();
    }

    public static String getRandomRGBAColor() {
        Random random = new Random();
        int red = random.nextInt(256); // Valeur de rouge entre 0 et 255
        int green = random.nextInt(256); // Valeur de vert entre 0 et 255
        int blue = random.nextInt(256); // Valeur de bleu entre 0 et 255

        return String.format("rgba(%d, %d, %d)", red, green, blue);
    }

    public static String getColor(Classementparequipeavecpointparcategorie[] stats) {
        String[] colors = new String[stats.length];
        for (int i = 0; i < stats.length; i++) {
            colors[i] = getRandomRGBAColor();
        }
        return new JSONArray(colors).toString();
    }

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
