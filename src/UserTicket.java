/**
 * Created by watcharaphat on 9/17/2016 AD.
 */
public class UserTicket {
    private String theaterName;
    private String mySeat;
    private boolean exist;

    UserTicket(String theaterName) {
        this.theaterName = theaterName;
        this.mySeat = "";
        this.exist = false;
    }

    public String toString() {
        return "Theater: " + theaterName + ", Seat: " + mySeat;
    }

    public boolean isExist() {
        return this.exist;
    }

    public void onUserTicketAddTicket(String seat) {
        if (!this.exist) {
            this.mySeat += seat;
            this.exist = true;
        } else {
            this.mySeat += ", " + seat;
        }
    }
}
