import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * TimeStamp for display time in log.
 */

public class TimeStamp {
    public String getTimeStamp() {
        Date date = new Date();
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);

        return timeStamp;
    }
}
