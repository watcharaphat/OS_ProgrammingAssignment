import java.util.*;

/**
 * The class that is supposed to be Theater. It contain seat, ticket and movie.
 */
public class Theater {
    private String movieName;
    private Vector<String> availableSeat;
    private Ticket[] ticket;

    public static Random rand = new Random();

    Theater(String movieName) {
        this.movieName = movieName;
        this.availableSeat = new Vector<String>();
        this.ticket = new Ticket[20];

        int p = 0;
        for (int i = 'A'; i < 'E'; i++) {
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

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void ReserveSeat(String s) {
        this.availableSeat.remove(s);
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

        for (int i = 0; i < 20; i++) {

            System.out.print(ticket[i] + "\t\t");

            if (i % 5 == 4)
                System.out.println();
        }

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public void offerSeat(MyThread T, int n, int position, TimeStamp TS) {
        for (int i = 0; i < n; i++) {
            if (this.coutAvailableSeat() == 0) {
                return;
            }

            // one ticket for each i
            int r = rand.nextInt(this.coutAvailableSeat());

            String s = "";

            try {
                s = availableSeat.get(r);
            } catch (ArrayIndexOutOfBoundsException e) {
                return;
            }

            ReserveSeat(s);


            int decisionTime = rand.nextInt(40000) + 1;

            // use decisionTime = 0 for non-delay testing.
            // int decisionTime = 0;
			
			if (decisionTime > 30000) {
                timeOutAt30Sec(T, s, TS);
                return;
            }

            T.sleepThread(decisionTime);

            if (T.decisionAccept() && s != "") {
                System.out.println("[" + TS.getTimeStamp() + "]" + " Accept seat " + "[" + movieName + ": " + s + "]\t" + "user: " + T.getThreadName());
                T.addTicket(position, s);
                ticket[getIndexFromSeat(s)].onTicketAddUser(T.getThreadName());
            } else {
                System.out.println("[" + TS.getTimeStamp() + "]" + " Cancel seat " + "[" + movieName + ": " + s + "]\t" + "user: " + T.getThreadName());
                CancelSeat(s);
            }
        }
    }

    public void timeOutAt30Sec(MyThread T, String s, TimeStamp TS) {
        T.sleepThread(30000);
        System.out.println("[" + TS.getTimeStamp() + "]" + " Time-out at " + "[" + movieName + ": " + s + "]\t" + "user: " + T.getThreadName());
        CancelSeat(s);
    }
}
