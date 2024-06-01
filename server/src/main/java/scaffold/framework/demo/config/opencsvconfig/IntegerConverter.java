package scaffold.framework.demo.config.opencsvconfig;

public class IntegerConverter {

    public static Integer convert(String value) {
        // Supprimer les caractères non numériques et les espaces
        value = value.replaceAll("[^\\d]", "").replace(" ", "");
        return Integer.parseInt(value);
    }
}