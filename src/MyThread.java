import java.util.Random;

/**
 * Created by watcharaphat on 9/11/2016 AD.
 */
public class MyThread implements Runnable {
    private Thread t;
    private String threadName;
    public UserTicket[] userTicket;


    public static Theater[] m = new Theater[5];
    public static String[] Movies = {"Fanday", "Me Before You", "Kubo", "W", "Divergent"};
    public static TimeStamp TS = new TimeStamp();
    public static Random rand = new Random();

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
            for (int i = 0; i < 100; i++) {
                getRandomTheaterTicket();
                Thread.sleep(50);
            }

            if(threadName == "Apple")
                Thread.sleep(1000);
            else if(threadName == "Mango")
                Thread.sleep(2000);
            else if(threadName == "Tomato")
                Thread.sleep(3000);
            else if(threadName == "Melon")
                Thread.sleep(4000);
            else if(threadName == "SuperTamz")
                Thread.sleep(5000);

        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }

        showMyTicket();
        System.out.println("Thread " + threadName + " exiting.\n");
    }

    public void showMyTicket() {
        System.out.println("Thread: " + threadName + ", Result\n************************************");
        for (int i = 0; i < 5; i++) {
            if (userTicket[i].isExist()) {
                System.out.println("Thread: " + threadName + ", " + userTicket[i]);
            }
        }
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
            if(m[r].coutAvailableSeat() > 0)
                m[r].offerSeat(this, rand.nextInt(m[r].coutAvailableSeat() + 1), r);
        } catch (NullPointerException e) {
            System.out.println("Thread: " + threadName + ", NullPointerException " + "r: " + r);
        }

        // userTicket[r].onUserTicketAddTicket(v.get(i).toString());
    }

    public boolean decisionAccept() {
        if (rand.nextInt(2) > 0)
            return true;
        else
            return false;
    }

    public static void initTheater() {
        for (int i = 0; i < 5; i++) {
            m[i] = new Theater(Movies[i]);
        }
    }

    public static void main(String[] args) {
        initTheater();

        MyThread T1 = new MyThread("Apple");
        T1.start();

        MyThread T2 = new MyThread("Mango");
        T2.start();

        MyThread T3 = new MyThread("Tomato");
        T3.start();

        MyThread T4 = new MyThread("Melon");
        T4.start();

        MyThread T5 = new MyThread("SuperTamz");
        T5.start();
    }

}
