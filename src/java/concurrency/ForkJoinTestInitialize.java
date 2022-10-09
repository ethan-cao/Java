package concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class ForkJoinTestInitialize extends RecursiveAction {
	private static final int THRESHOLD = 10000;
	private int[] data;
	private int start;
	private int end;

	public static void main(String[] args) {
	     int[] data = new int[10_000_000];
	     ForkJoinPool fjPool = new ForkJoinPool();

		 // initialize a large array
	     ForkJoinTestInitialize action =  new ForkJoinTestInitialize(data, 0, data.length);

	     fjPool.invoke(action);
	}
	
	ForkJoinTestInitialize(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
	    // is it a manageable amount of work?
		if (end - start <= THRESHOLD) {
			for (int i = start; i < end; i++) {
				data[i] = ThreadLocalRandom.current().nextInt();
			}
		} else { 
			// task too big, split it
			int halfWay = (end + start) >>> 1; // @see BinarySearch.java
			ForkJoinTestInitialize a1 = new ForkJoinTestInitialize(data, start, halfWay);
			ForkJoinTestInitialize a2 = new ForkJoinTestInitialize(data, halfWay, end);

			a1.fork(); // a1 will be picked by another thread
			a2.compute(); // current thread keeps working on a2
			a1.join(); // wait for a1 to be complete

			// invokeAll(a2, a1);  // convenience method that can replace the above 3 lines (fork, logStar, join)
		}
	}
}
