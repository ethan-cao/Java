package concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTestSearch extends RecursiveTask<Integer> {
	private static final int THRESHOLD = 10000;
	private int[] data;
	private int start;
	private int end;

	 public static void main(String[] args) {
	     int[] data = new int[10000000];
	     ForkJoinPool fjPool = new ForkJoinPool();

	     // initialize the array
	     ForkJoinTestInitialize action =  new ForkJoinTestInitialize(data, 0, data.length);
	     fjPool.invoke(action); 

	     // find the max value index
	     ForkJoinTestSearch task = new ForkJoinTestSearch(data, 0, data.length);
	     Integer position = fjPool.invoke(task);
	     
	     System.out.println("Position: " + position + ", value: " +  data[position]);
	 }
	 
	ForkJoinTestSearch(int[] data, int start, int end) { this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	// looks for the max value's position
	protected Integer compute() {
		if (end - start <= THRESHOLD) { // is it a manageable amount of work?
			int position = 0;           // if all values are equal, return position 0 
			for (int i = start; i < end; i++) {
				if (data[i] > data[position]) { 
					position = i;
				} 
			}

			return position;
		} else { // task too big, split it
			int halfWay = ((end - start) / 2) + start; 
			ForkJoinTestSearch t1 = new ForkJoinTestSearch(data, start, halfWay);
			ForkJoinTestSearch t2 = new ForkJoinTestSearch(data, halfWay, end);

			t1.fork(); // queue left half of task 
			int position2 = t2.compute(); // work on right half of task
			int position1 = t1.join(); // wait for queued task to be complete 
			
			// out of the position in two subsection which is greater?
			if (data[position1] > data[position2]) {
				return position1;
			} else if (data[position1] < data[position2]) {
				return position2; 
			} else {
				return position1 < position2 ? position1 : position2;
			}
		}
	} 
}