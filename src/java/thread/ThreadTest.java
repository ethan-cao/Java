package thread;

public class ThreadTest extends Thread {
    public static void main(String[] args) {
        Runnable target = new Target();
        Thread thread1 = new Thread(target);
        thread1.start();
//        thread1.start();  // start() can be invoked only once, otherwise runtime exception

        Thread thread2 = new MyThread();
        thread2.start();

        // returns a reference to the currently executing thread
        Thread current = Thread.currentThread();
        System.out.println("current thread ID : " +  current.getId() );
        System.out.println("current thread name : " +  current.getName() );
    }
}

class Target implements Runnable {
    @Override
    public void run() {
        System.out.println("job in run 1");

        try {
            // static method
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("job in run 2");
    }
}

/**
 If there are 2 different threads that call read() and write() independently, there is a risk of deadlock
 at synchronized (resourceB) in read() and synchronized (resourceA) in write()
 The reader thread will have resourceA, the writer thread will have resourceB,
 and both will get stuck as the reader thread is waiting for recourceB while the write thread waiting for resourceA

 However, code like this almost never results in deadlock because the CPU has to switch from the reader thread to the
 writer thread at a particular point in the code, and the chances of deadlock occurring are very small. 
 The application may work fine 99.9 percent of the time.
 */
class DeadlockRisk {
    private static class Resource {
        public int value;
    }

    private Resource resourceA = new Resource();
    private Resource resourceB = new Resource();

    public int read() {
        //synchronized block can only sync on Object,  not primitive
        synchronized (resourceA) {
            synchronized (resourceB) {
                return resourceB.value + resourceA.value;
            }
        }
    }

    public void write(int a, int b) {
        synchronized (resourceB) {
            synchronized (resourceA) {
                resourceA.value = a;
                resourceB.value = b;
            }
        }
    }
}