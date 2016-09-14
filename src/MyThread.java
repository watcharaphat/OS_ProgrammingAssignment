import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by watcharaphat on 9/11/2016 AD.
 */
public class MyThread implements Runnable {
    private Thread t;
    private String threadName;
    private ArrayList<UserTicket> ticket = new ArrayList<UserTicket>();


    public static TimeStamp TS = new TimeStamp();
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static String s = "ABCDFGHIJKLMNOPQRSTUVWXYZ";

    MyThread(String name) {
        this.threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for(int i = 0; i < 10; i++) {
                char c = s.charAt(s.length()-1);
                s = s.substring(0, s.length()-1);
                System.out.println("Thread " + threadName + ", i:" + i + " c: " + c  + "\t" +  TS.getTimeStamp());
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
        // System.out.println("Thread " + threadName + " final x: " + x );
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if(t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void randTicket(Theater m) {

    }

    public static void main(String[] args) {

        MyThread T1 = new MyThread("T1");
        T1.start();
        MyThread T2 = new MyThread("T2");
        T2.start();
    }

}
