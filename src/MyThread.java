import java.util.Random;
import java.util.Vector;

/**
 * Created by watcharaphat on 9/11/2016 AD.
 */
public class MyThread implements Runnable {
    private Thread t;
    private String threadName;
    public UserTicket[] userTicket;

    public static Theater[] m = new Theater[5];

    public static String[] Movies = {"Fanday", "Me Before You", "Kubo", "W", "Captain America: CIVIL WAR"};
    public static TimeStamp TS = new TimeStamp();
    public static Random rand = new Random();

    // public static String s = "ABCDFGHIJKLMNOPQRSTUVWXYZ";

    MyThread(String name) {
        this.threadName = name;
        System.out.println("Creating " + threadName);
        userTicket = new UserTicket[5];
    }

    public String getThreadName() {
        return this.threadName;
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 0; i < 10; i++) {
                getRandomTheaterAndTicket();
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }

        System.out.println("Thread: " + threadName + ", Result");
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void showMyTicket() {
        for (int i = 0; i < 5; i++) {

        }
    }

    /*

    // this is the code for testing thread.

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 0; i < 10; i++) {
                char c = s.charAt(s.length() - 1);
                s = s.substring(0, s.length() - 1);
                System.out.println("Thread " + threadName + ", i:" + i + " c: " + c + "\t" + TS.getTimeStamp());
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");

    }
    */

    public void start() {
        initTicket();

        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void initTicket() {
        this.userTicket = new UserTicket[5];
        for (int i = 0; i < 5; i++) {
            this.userTicket[i] = new UserTicket(Movies[i]);
        }
    }

    public void getRandomTheaterAndTicket() {
        int r = rand.nextInt(5);
        try {
            m[r].offerSeat(this, rand.nextInt(5) + 1, r);
        } catch (NullPointerException e) {
            System.out.println("Thread: " + threadName + ", NullPointerException " + "r: " + r);
        }

        // userTicket[r].onUserTicketAddTicket(v.get(i).toString());
    }

    private boolean decition() {
        if (rand.nextInt(2) > 0)
            return true;
        else
            return false;
    }

    public static void initTheater() {
        for (int i = 0; i < 4; i++) {
            m[i] = new Theater(Movies[i]);
        }
    }

    public static void main(String[] args) {
        initTheater();

        MyThread T1 = new MyThread("Apple");
        T1.start();

        MyThread T2 = new MyThread("Mango");
        T2.start();
    }

}
