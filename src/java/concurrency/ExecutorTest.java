package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
    public static void main(String[] args) {
        Runnable r = new MyRunnableTask();

        Executor ex1 = new SameThreadExecutor();
        ex1.execute(r);

        Executor ex2 = new NewThreadExecutor();
        ex2.execute(r);




        ExecutorService ex3 = Executors.newCachedThreadPool();
        ex3.execute(r);

        ExecutorService ex4 = Executors.newFixedThreadPool(4);
        ex4.execute(r);

        ExecutorService ex5 = Executors.newSingleThreadExecutor();
        ex5.execute(r);

        ExecutorService ex6 = Executors.newScheduledThreadPool(4);
        ex6.execute(r);
    }
}

class MyRunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable Task");
    }
}

class SameThreadExecutor implements Executor {
    // executes the Runnable using the thread that invokes this execute()
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}

class NewThreadExecutor implements Executor {
    // executes the Runnable using a new Thread
    @Override
    public void execute(Runnable command) {
        Thread t = new Thread(command);
        t.start();
    }
}