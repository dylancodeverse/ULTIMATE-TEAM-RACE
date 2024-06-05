package scaffold.framework.demo.config.opencsvconfig;


public class StringConvert {
    public static String convert(String frDAte) {
        return frDAte.replaceAll("'", "");
    }
}
