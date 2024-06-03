package scaffold.framework.demo.config.opencsvconfig;

import java.sql.Date;

public class DateConvert {
    public static Date convert(String frDAte) {
        String[] ddmmyyyy = frDAte.trim().split("/");
        return Date.valueOf(ddmmyyyy[2] + "-" + ddmmyyyy[1] + "-" + ddmmyyyy[0]);

    }
}
