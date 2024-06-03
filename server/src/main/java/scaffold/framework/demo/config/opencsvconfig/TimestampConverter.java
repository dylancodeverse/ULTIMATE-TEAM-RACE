package scaffold.framework.demo.config.opencsvconfig;

import java.sql.Date;
import java.sql.Timestamp;

public class TimestampConverter {
    public static Timestamp convert(String dateFR, String time) {
        String[] ddmmyyyy = dateFR.trim().split("/");
        Date date = Date.valueOf(ddmmyyyy[3] + "-" + ddmmyyyy[1] + "-" + ddmmyyyy[0]);
        return Timestamp.valueOf(date + " " + time.trim());
    }
}
