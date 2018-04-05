package thread;

public class ThreadSleepTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(r, "One ");
        t1.start();
        t1.sleep(2000);

        Thread t2 = new Thread(r, "Two ");
        t2.start();
        t2.sleep(1000);

        System.out.println("Main");
    }
}
