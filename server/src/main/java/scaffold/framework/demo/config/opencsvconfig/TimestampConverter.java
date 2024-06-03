package scaffold.framework.demo.config.opencsvconfig;

import java.sql.Date;
import java.sql.Timestamp;

public class TimestampConverter {
    public static Timestamp convert(String dateFR, String time) {
        String[] ddmmyyyy = dateFR.trim().split("/");
        Date date = Date.valueOf(ddmmyyyy[2] + "-" + ddmmyyyy[1] + "-" + ddmmyyyy[0]);
        return Timestamp.valueOf(date + " " + time.trim());
    }

    public static Timestamp convert(String timestampFR) {
        String[] datetime = timestampFR.trim().split(" ");
        return TimestampConverter.convert(datetime[0], datetime[1]);

    }
}
