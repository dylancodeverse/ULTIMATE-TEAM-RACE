package scaffold.framework.demo.config.opencsvconfig;

public class PourcentageConverter {

    public static Double convert(String value) {
        value = value.replaceAll("[^\\d.,]", "").replace(" ", "");
        value = value.replace(",", ".");
        return Double.parseDouble(value) / 100.;
    }
}