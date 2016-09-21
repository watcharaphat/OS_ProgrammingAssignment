import java.util.Collection;
import java.util.Vector;

/**
 * Created by watcharaphat on 9/14/2016 AD.
 */

public class Ticket {
    private String Seat;
    private Vector userName;

    Ticket(String seat) {
        this.Seat = seat;
        userName = new Vector();
    }

    public String toString() {
        String s = this.Seat + ": ";
        for(int i = 0; i < userName.size(); i++) {
            if (i == 0)
                s += userName.get(i);
            else
                s += (", " + userName.get(i));
        }
        return s;
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
