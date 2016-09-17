import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by watcharaphat on 9/14/2016 AD.
 */

public class Ticket {
    private String Seat;
    private Vector userName;
    // private Vector<Ticket> vtTicket = new Vector<Ticket>();

    Ticket(String seat) {
        this.Seat = seat;
        userName = new Vector();
    }

    public void setSeat(String s) {
        this.Seat = s;
    }

    public void onTicketAddUser(String userName) {
        this.userName.add(userName);
    }

    public void onTicketAddUserAll(Collection c) {
        this.userName.addAll(c);
    }
}
