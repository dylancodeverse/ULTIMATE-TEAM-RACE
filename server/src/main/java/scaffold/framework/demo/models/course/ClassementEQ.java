package scaffold.framework.demo.models.course;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Random;

import org.json.JSONArray;

import orm.DynamicORM;
import scaffold.framework.demo.models.pdf.PdfGenerator;

public class ClassementEQ extends DynamicORM<ClassementEQ> {
    Long rang;
    String equipe;
    Integer points;

    public byte[] generatePDFSelonPlace(Connection connection, String fileTemplate, String condition) throws Exception {

        ClassementEQ cls = selectWhere(connection, true, "rang=1")[0];
        return PdfGenerator.generatePdfFromHtml(fileTemplate,
                new String[] { "[Nom du Vainqueur]", "[Date de l'Événement]" },

                new String[] { cls.getEquipe(), LocalDate.now().toString() });
    }

    public static String[] getJSONPieChartInformation(ClassementEQ[] fs) throws Exception {
        return new String[] { ClassementEQ.getJSONDATA(fs), ClassementEQ.getColor(fs), ClassementEQ.getLabel(fs) };
    }

    public static String getLabel(ClassementEQ[] fs) {
        String[] labels = new String[fs.length];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = fs[i].getEquipe();
        }
        return new JSONArray(labels).toString();
    }

    public static String getJSONDATA(ClassementEQ[] stats) {
        Integer[] datas = new Integer[stats.length];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = stats[i].getPoints();
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

    public static String getColor(ClassementEQ[] stats) {
        String[] colors = new String[stats.length];
        for (int i = 0; i < stats.length; i++) {
            colors[i] = getRandomRGBAColor();
        }
        return new JSONArray(colors).toString();
    }

    public Long getRang() {
        return rang;
    }

    public void setPoints(BigDecimal pts) {
        setPoints(Integer.valueOf(pts.toPlainString()));
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
