import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by watcharaphat on 9/11/2016 AD.
 */

public class TimeStamp {
    public String getTimeStamp() {
        Date date = new Date();
        String TimeStamp = new Timestamp(date.getTime()).toString();
        return TimeStamp;
    }
}
