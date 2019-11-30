package lesson4.Task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final int COUNT = 3;
    private static final String LOG_FILE_NAME = "log";
    public static File logfile;

    public static void main(String[] args) {

        logfile = new File(LOG_FILE_NAME);
        if (!logfile.exists()) {
            try {
                logfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        init();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        service.scheduleAtFixedRate(new LogWriter(), 0, 20, TimeUnit.MILLISECONDS);


    }

    public static void addToLog(String msg) {
        synchronized (logfile) {
            try {
                FileWriter writer = new FileWriter(logfile, true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                for (int i = 0; i < COUNT; i++) {
                    bufferWriter.write(msg + "\n");
                }
                bufferWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void init() {
        try {
            FileWriter writer = new FileWriter(logfile);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write("");
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
