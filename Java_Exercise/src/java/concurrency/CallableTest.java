package concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CallableTest {

	public static void main(String[] args) {
		Callable<Integer> c = new MyCallable();
		ExecutorService ex = Executors.newCachedThreadPool();

		// Submitting a Callable to an ExecutorService returns a Future reference
		Future<Integer> f = ex.submit(c);

		try {
			Integer v = f.get(); // blocks until done, may throw exception
			System.out.println("Ran:" + v);
		} catch (InterruptedException | ExecutionException iex) {
			System.out.println("Failed");
		}

		// An ExecutorService should be shut down once it is no longer needed to free resource
		// no more new tasks but keep running to finish existing tasks
		ex.shutdown();

		try {
			// wait 2 seconds for running tasks to finish
			boolean term = ex.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException ex1) {
			// did not wait the full 2 seconds
		} finally {
			if (!ex.isTerminated()) {// are all tasks done?
				List<Runnable> unfinished = ex.shutdownNow();
				// a collection of the unfinished tasks
			}
		}

	}
}

class MyCallable implements Callable {
    @Override
	public Integer call() {
		// Obtain a random number from 1 to 10
		// A ThreadLocalRandom is unique to a thread
		// it performs better because it avoids any contention.
		int count = ThreadLocalRandom.current().nextInt(1, 11);

		for (int i = 1; i <= count; i++) {
			System.out.println("Running..." + i);
		}

		return count;
	}
}
