/**
 * UserTicket Class is the class to store user's holding tickets.
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
        if (seat == "")
            return;
        if (!this.exist) {
            this.mySeat += seat;
            this.exist = true;
        } else {
            this.mySeat += ", " + seat;
        }
    }
}
