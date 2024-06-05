package scaffold.framework.demo.config.opencsvconfig;

public class StringConvert {
    public static String convert(String frDAte) {
        if (frDAte == null) {
            return "";
        }
        return frDAte.replaceAll("'", "");
    }
}
