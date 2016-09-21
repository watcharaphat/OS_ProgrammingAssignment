import java.util.Random;

/**
 * Created by watcharaphat on 9/11/2016 AD.
 */
public class MyThread implements Runnable {
    private Thread t;
    private String threadName;
    private int threadNum;
    private UserTicket[] userTicket;

    public static Theater[] m = new Theater[5];
    public static String[] Movies = {"Zootopia", "Star Wars", "Inception", "Godzilla", "Divergent"};
    public static String[] Names = {"John", "Adam", "Alice", "Tony", "Rola"};
    public static TimeStamp TS = new TimeStamp();
    public static Random rand = new Random();
    public static boolean[] isEnd = {false, false, false, false, false};

    MyThread(String name, int threadNum) {
        this.threadName = name;
        System.out.println("Creating " + threadName);
        userTicket = new UserTicket[5];
        this.threadNum = threadNum;
    }

    public static boolean allIsEnd() {
        return isEnd[0] && isEnd[1] && isEnd[2] && isEnd[3] && isEnd[4];
    }

    public String getThreadName() {
        return this.threadName;
    }

    public void sleepThread(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            while (notOutOfSeat()) {
                getRandomTheaterTicket();
                Thread.sleep(1000);
            }

            Thread.sleep(10000);

            if (threadName == Names[0])
                Thread.sleep(1000);
            else if (threadName == Names[1])
                Thread.sleep(2000);
            else if (threadName == Names[2])
                Thread.sleep(3000);
            else if (threadName == Names[3])
                Thread.sleep(4000);
            else if (threadName == Names[4])
                Thread.sleep(5000);

        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }

        showMyTicket();
        isEnd[threadNum] = true;
        System.out.println("Thread " + threadName + " exiting.");
        if (allIsEnd()) {
            for (int i = 0; i < 5; i++)
                m[i].showTheaterResult(i + 1);
        }
    }

    public static boolean notOutOfSeat() {
        boolean available = true;

        if (m[0].coutAvailableSeat() == 0 && m[1].coutAvailableSeat() == 0 && m[2].coutAvailableSeat() == 0
            && m[3].coutAvailableSeat() == 0 && m[4].coutAvailableSeat() == 0)
            available = false;

        return available;
    }

    public void showMyTicket() {
        System.out.println("\nThread: " + threadName + ", Result");
        System.out.println("************************************************************");
        for (int i = 0; i < 5; i++) {
            if (userTicket[i].isExist()) {
                System.out.println("Thread: " + threadName + ", " + userTicket[i]);
            }
        }
    }

    public void addTicket(int position, String seat) {
        userTicket[position].onUserTicketAddTicket(seat);
    }

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

    public void getRandomTheaterTicket() {
        int r = rand.nextInt(5);
        try {
            if (m[r].coutAvailableSeat() > 0) {
                int n = rand.nextInt(m[r].coutAvailableSeat() + 1);

                if (n > 5) n = 5;

                m[r].offerSeat(this, n, r, TS);
            }
        } catch (NullPointerException e) {
            System.out.println("Thread: " + threadName + ", NullPointerException " + "r: " + r);
        }
    }

    public boolean decisionAccept() {
        return rand.nextInt(2) > 0;
    }

    public static void initTheater() {
        for (int i = 0; i < 5; i++) {
            m[i] = new Theater(Movies[i]);
        }
    }

    public static void main(String[] args) {
        initTheater();

        MyThread T1 = new MyThread(Names[0], 0);
        T1.start();

        MyThread T2 = new MyThread(Names[1], 1);
        T2.start();

        MyThread T3 = new MyThread(Names[2], 2);
        T3.start();

        MyThread T4 = new MyThread(Names[3], 3);
        T4.start();

        MyThread T5 = new MyThread(Names[4], 4);
        T5.start();

    }
}
