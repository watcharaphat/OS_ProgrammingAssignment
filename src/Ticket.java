import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by watcharaphat on 9/14/2016 AD.
 */

public class Ticket {
    private String Seat;
    private Vector userName;

    Ticket() {
        this.userName = new Vector();
    }

    public void setSeat(String s) {
        this.Seat = s;
    }

    public void onTicketAddUser(String userName) {
        this.userName.add(userName);
    }
}
