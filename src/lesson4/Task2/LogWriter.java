package lesson4.Task2;

public class LogWriter implements Runnable {



    @Override
    public void run() {
            Main.addToLog(Thread.currentThread().getName());
    }
}
