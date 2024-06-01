package scaffold.framework.demo.config.opencsvconfig;

// Convertisseur personnalisé pour les attributs de type Double
public class DoubleConverter {

    public static Double convert(String value) {
        // Supprimer les caractères non numériques et les espaces
        value = value.replaceAll("[^\\d.,]", "").replace(" ", "");
        // Remplacer les virgules par des points pour les nombres décimaux
        value = value.replace(',', '.');
        return Double.parseDouble(value);
    }
}
