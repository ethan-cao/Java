package concurrency;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
	public static void main(String[] args){
		MaxValueCollection mvc = new MaxValueCollection();
		
		mvc.add(2);
		mvc.add(3);
		mvc.add(4);
		
		System.out.println(mvc.findMax());
	}
}

// this shows how to make ArrayList thread-safe
// allowing concurrent reads but exclusive access by a writing thread:
class MaxValueCollection{
	private List<Integer> integers = new ArrayList<>();
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void add(Integer i){
		rwl.writeLock().lock(); // One at a time

		try{
			integers.add(i);
		}finally{
			rwl.writeLock().unlock();
		}
	}

	public int findMax(){
		rwl.readLock().lock(); // Many at once
	
		try{
			return Collections.max(integers);
		} finally{
			rwl.readLock().unlock();
		}
	}
}

