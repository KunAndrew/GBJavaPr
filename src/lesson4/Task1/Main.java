package lesson4.Task1;

public class Main {
    private static final int COUNT =5 ;
    public volatile static Object monitor=new Object();
    public static String flag="A";
    public static void main(String[] args) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<COUNT;i++){
                    synchronized (monitor) {

                        while(!flag.equals("A")) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (flag.equals("A")){
                    System.out.println(Thread.currentThread().getName() + " A");
                        flag="B";}
                        monitor.notifyAll();

                }}}
            }, "1").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<COUNT;i++){
                    synchronized (monitor) {

                        while(!flag.equals("B")) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (flag.equals("B")) {
                            System.out.println(Thread.currentThread().getName() + " B");
                            flag="C";
                        }
                        monitor.notifyAll();

                }}}
            }, "2").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<COUNT;i++){
                    synchronized (monitor) {

                        while(!flag.equals("C")) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (flag.equals("C")) {
                            System.out.println(Thread.currentThread().getName() + " C");
                            flag="A";
                        }
                        monitor.notifyAll();
                }}}
            }, "3").start();

    }
}
