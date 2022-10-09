package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicOperationTest {

	public static void main(String[] args) {
		test1();
	}

	static void test1(){
		AtomicInteger i = new AtomicInteger();
		// initial value is 0

		// increase i by 1, return the previous value
		System.out.println(i.getAndIncrement()); // i is already 1
		System.out.println(i);

		// increase i by 1, return the update value
		System.out.println(i.incrementAndGet());  // i is already 2
		System.out.println(i);

	}

	static void test2() {
		Counter counter = new Counter();

		IncrementerThread it1 = new IncrementerThread(counter);
		IncrementerThread it2 = new IncrementerThread(counter);

		it1.start();
		it2.start();

		try {
			it1.join();
			it2.join();
		} catch (Exception e) {
		}

		System.out.println(counter.getValue());
	}
}

class Counter{
//	private int count = 0;   // not thread safe
	private AtomicInteger count = new AtomicInteger();  // thread safe, using class from java.util.concurrent.atomic
	
	public void increment(){
	    count.getAndIncrement(); // Atomic operation	
	    // atomic operation using Compare And Swap
	}
	
	public int getValue(){
		return count.intValue();
	}
}

class IncrementerThread extends Thread{
	private Counter counter;
	
	public IncrementerThread (Counter counter){
		this.counter = counter;
	}
	
	public void run(){
		for (int i=0; i<1000; i++){
			counter.increment();
		}
	}
}