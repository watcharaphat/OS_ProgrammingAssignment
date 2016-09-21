import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by watcharaphat on 9/11/2016 AD.
 */

public class TimeStamp {
    public String getTimeStamp() {
        Date date = new Date();
        //String TimeStamp = new Timestamp(date.getTime()).toString();
        String TimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
        return TimeStamp;
    }
}
