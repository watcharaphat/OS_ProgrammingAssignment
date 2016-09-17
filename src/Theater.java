import java.util.*;

/**
 * Created by watcharaphat on 9/12/2016 AD.
 */
public class Theater {
    private String movieName;
    private Vector<String> availableSeat = new Vector<String>();
    private Ticket[] ticket = new Ticket[20];
    // private Map seatMap = new HashMap();
    // private ArrayList user = new ArrayList();

    public static Random rand = new Random();

    public static Theater[] m = new Theater[5];

    Theater(String movieName) {
        this.movieName = movieName;

        int p = 0;
        for(int i = 65; i < 69; i++) {
            for(int j = 1; j < 5; j++) {
                availableSeat.add(Character.toString((char) i) + j);
                ticket[p++] = new Ticket( (Character.toString((char) i ) + j) );
            }
        }
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

    public void CancelTSeats(Vector v) {
        this.availableSeat.addAll(v);
    }

    public static int getIndexFromSeat(String seat) {
        return ( (int)seat.charAt(0) - 'A' )*4 + ( (int)seat.charAt(1) - '1');
    }

    public void offerSeat(MyThread T ,int n, int position) {
        Vector vtOfferSeat = new Vector();
        for(int i = 0; i < n; i++) {
            if(n > availableSeat.size()) {
                System.out.println("Out of ticket.");
                break;
            }

            int r = rand.nextInt(this.availableSeat.size());
            String s = availableSeat.get(r);

            ReserveSeat(s);

            ticket[getIndexFromSeat(s)].onTicketAddUser(T.getThreadName());
            T.userTicket[position].onUserTicketAddTicket(s);

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
