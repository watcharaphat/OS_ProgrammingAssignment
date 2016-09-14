import java.util.*;

/**
 * Created by watcharaphat on 9/12/2016 AD.
 */
public class Theater {
    private String movieName;
    private Map seatMap = new HashMap();
    private ArrayList user = new ArrayList();
    Vector seat = new Vector();

    public static Random rand = new Random();


    Theater(String movieName) {
        this.movieName = movieName;

        for(int i = 65; i < 69; i++) {
            for(int j = 1; j < 5; j++) {
                seat.add(Character.toString((char) i) + j);
            }
        }
    }

    public static void main(String[] args) {
        Theater m1 = new Theater("Fanday");
        System.out.println(m1.seat);
        System.out.println(rand.nextInt(2));
    }
}
