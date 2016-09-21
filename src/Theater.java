import java.util.*;

/**
 * Created by watcharaphat on 9/12/2016 AD.
 */
public class Theater {
    private String movieName;
    private Vector<String> availableSeat = new Vector<String>();
    private Ticket[] ticket = new Ticket[20];

    public static Random rand = new Random();

    public static Theater[] m = new Theater[5];

    Theater(String movieName) {
        this.movieName = movieName;

        int p = 0;
        for (int i = 65; i < 69; i++) {
            for (int j = 1; j <= 5; j++) {
                availableSeat.add(Character.toString((char) i) + j);
                ticket[p++] = new Ticket((Character.toString((char) i) + j));
            }
        }
    }

    public int coutAvailableSeat() {
        return this.availableSeat.size();
    }

    public String getMovieName() {
        return this.movieName;
    }

    public void setName(String movieName) {
        this.movieName = movieName;
    }

    public void ReserveSeat(String s) {
        this.availableSeat.remove(s);
    }

    public void CancelSeats(Vector v) {
        this.availableSeat.addAll(v);
    }

    public void CancelSeat(String s) {
        if (s == "") return;

        this.availableSeat.add(s);
    }

    public static int getIndexFromSeat(String seat) {
        return (seat.charAt(0) - 'A') * 5 + (seat.charAt(1) - '1');
    }

    public void showTheaterResult(int theaterNum) {

        System.out.println("\nTheater: " + theaterNum + ", Movie: " + this.movieName);

        for(int i = 0; i < 20; i++) {

            System.out.print(ticket[i] + "\t\t");

            if(i%5 == 4)
                System.out.println();
        }

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public void offerSeat(MyThread T, int n, int position, TimeStamp TS) {
        // if(availableSeat.size() < n)
        //    return;

        for (int i = 0; i < n; i++) {
            if (this.coutAvailableSeat() == 0) {
                // System.out.println("Out of ticket.");
                break;
            }

            // one ticket for each i
            int r = rand.nextInt(this.coutAvailableSeat());

            String s = "";

            try {
                s = availableSeat.get(r);
            } catch (ArrayIndexOutOfBoundsException e) {
                // System.out.println("ArrayIndexOutOfBoundException: " + e);
                return;
            }

            ReserveSeat(s);

            // T.sleepThread(rand.nextInt(100) + 1);

            if (T.decisionAccept() && s != "") {
                System.out.println("[" + TS.getTimeStamp() + "]" + " Accept seat " + "[" + movieName + ": " + s + "]\t" + " for user: " + T.getThreadName());
                // T.userTicket[position].onUserTicketAddTicket(s);
                T.addTicket(position, s);
                ticket[getIndexFromSeat(s)].onTicketAddUser(T.getThreadName());
            } else {
                System.out.println("[" + TS.getTimeStamp() + "]" + " Cancel seat " + "[" + movieName + ": " + s + "]\t" + " for user: " + T.getThreadName());
                CancelSeat(s);
            }

            // Add user to ticket

        }
    }


    // For testing this class.

    public static void main(String[] args) {
        Theater m1 = new Theater("Fanday");
        System.out.println(m1.availableSeat);
        System.out.println(rand.nextInt(2));

        m[0] = new Theater("Fanday");
        System.out.println(getIndexFromSeat("E4"));
    }
}
