package lesson4.Task3;

public class MFU {
    Object printMutex = new Object();
    Object scanMutex = new Object();

    public void print(String file, int pages) throws InterruptedException {
        synchronized (printMutex) {
            for (int i = 0; i < pages; ++i) {
                System.out.println("Отпечатано "+i+" страниц "+file );
                Thread.sleep(100);
            }
        }
    }

    public void scan(String file, int pages) throws InterruptedException {
        synchronized (scanMutex) {
            for (int i = 0; i < pages; ++i) {
                System.out.println("Отсканировано "+i+" страниц "+file);
                Thread.sleep(100);
            }
        }
    }

    public static void main(String[] args) {
        MFU mfu = new MFU();
Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mfu.print("File1",15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mfu.print("File2",10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mfu.scan("File3",5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
