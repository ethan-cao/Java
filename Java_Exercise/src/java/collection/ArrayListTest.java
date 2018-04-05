package collection;

import java.util.*;

public class ArrayListTest {
    // Check CollectionTest.java & AccessModifierTest.java to understand protected
	protected int i = 1;

	public static void main(String[] args) {
	}
	
	static void test1(){
		List<Integer> al = new ArrayList<Integer> (3);
		
		System.out.println(al.size());
		
		al.add(1); al.add(2); al.add(3);  // add in the end  al= 1, 2, 3
		al.add(1, 5);        // add 5 to the index 1     al= 1, 5, 2, 3
		for(Integer i: al)
			System.out.print(i);
		
		System.out.println("--------");
		System.out.println("contains: "+ al.contains(2) ); // Returns whether the element is in the list
		System.out.println("get: "+ al.get(3));            // Returns the Object located at index
		System.out.println("indexOf: "+ al.indexOf(11));    // Returns the index of the element, or -1 if the Object is not found
		
		// al.remove(x);        
			//if x is index,  Removes the element at that index and shifts later elements toward the beginning one space
			//if x is object, Removes the first!!! occurrence of the Object and shifts later elements toward the beginning one space
		
		al.clear();    // Removes all the elements from the ArrayList
		System.out.println("after clear");
		for(Integer i: al)
			System.out.print(i);
	}

	static void autoboxingTest(){
		Integer y = new Integer(567);    // make it
	    y++;                             // unwrap it, increment it,
	                                     // rewrap it
	    System.out.println("y = " + y);  // print it
		
	}

	static void stackTest(){
		Deque<String> deq = new ArrayDeque<String>(2);
		deq.addFirst("one");
		deq.addFirst("two");
		deq.addFirst("three"); // Line 9

		System.out.print(deq.pollLast());
		System.out.print(deq.pollLast());
		System.out.print(deq.pollLast()); // Line 12
	}
}
