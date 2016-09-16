import java.util.*;

/**
 * Created by watcharaphat on 9/12/2016 AD.
 */
public class Theater {
    private String movieName;
    private Vector<Ticket> vtTicket = new Vector<Ticket>();
    private Vector<String> availableSeat = new Vector<String>();

    // private Map seatMap = new HashMap();
    // private ArrayList user = new ArrayList();

    public static Random rand = new Random();

    public static Theater[] m = new Theater[5];

    Theater(String movieName) {
        this.movieName = movieName;

        for(int i = 65; i < 69; i++) {
            for(int j = 1; j < 5; j++) {
                availableSeat.add(Character.toString((char) i) + j);
            }
        }
    }

    public String getMovieName() {
        return this.movieName;
    }

    public void setName(String movieName) {
        this.movieName = movieName;
    }

    private void ReserveSeat(String s) {
        this.availableSeat.remove(s);
    }

    private void CancelTSeats(Vector v) {
        this.availableSeat.addAll(v);
    }

    public Vector offerSeat(int n) {
        Vector vtOfferSeat = new Vector();
        for(int i = 0; i < n; i++) {
            int r = rand.nextInt(this.availableSeat.size());
            String s = (String) availableSeat.get(r);
            vtOfferSeat.add(s);
            ReserveSeat(s);
        }

        return vtOfferSeat;
    }


    public static void main(String[] args) {
        Theater m1 = new Theater("Fanday");
        System.out.println(m1.availableSeat);
        System.out.println(rand.nextInt(2));

        m[0] = new Theater("Fanday");
    }
}
