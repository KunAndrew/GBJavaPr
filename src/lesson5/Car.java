package lesson5;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT_NAME;
    final static CountDownLatch cdlPreparing = new CountDownLatch(MainClass.CARS_COUNT);
    final static CountDownLatch cdlRaceStart = new CountDownLatch(MainClass.CARS_COUNT);
    final static CountDownLatch cdlRaceFinish = new CountDownLatch(MainClass.CARS_COUNT);

    static {
        CARS_COUNT_NAME = 0;

    }

    private Race race;
    private int speed;
    private String name;
    private static volatile boolean winFlag = false;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT_NAME++;
        this.name = "Участник #" + CARS_COUNT_NAME;
    }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));

            System.out.println(this.name + " готов");
            cdlPreparing.countDown();
            cdlRaceStart.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            cdlPreparing.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (!winFlag) {
            System.out.println("WIN");
            winFlag = true;
        }
        cdlRaceFinish.countDown();
    }
}
