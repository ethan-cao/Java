package collection;

import java.util.*;

public class ArrayListTest {
    // Check CollectionTest.java & AccessModifierTest.java to understand protected
	protected int i = 1;

	public static void main(String[] args) {
		test1();
	}
	
	static void test1(){
		//index starts from 0
		List<Integer> al = new ArrayList<> (3);

//		al.get(0); // this causes runtime exception IndexOutOfBoundsException
		
		System.out.println(al.size()); // 0
		
		al.add(1);
		al.add(2);
		al.add(3);  // add in the end  al= 1, 2, 3

		// also works with nested ArrayList
		System.out.println("print ArrayList: " + al); // [1, 2, 3]


		Integer[] array = al.toArray(new Integer[al.size()]);  // convert arrayList to array

		al.add(1, 5);        // add 5 to the index 1     al= 1, 5, 2, 3
		for(Integer i: al){
			System.out.print(i);
		}

		System.out.println("--------");
		System.out.println("contains: "+ al.contains(2) ); // Returns whether the element is in the list
		System.out.println("get: "+ al.get(3));            // Returns the Object located at index
		System.out.println("indexOf: "+ al.indexOf(11));    // Returns the index of the element, or -1 if the Object is not found
		
		// al.remove(x);        
		//if x is index,  Removes the element at that index and shifts later elements toward the beginning one space
		//if x is object, Removes the first!!! occurrence of the Object and shifts later elements toward the beginning one space
		
		al.clear();    // Removes all the elements from the ArrayList

		// print entire ArrayList
		System.out.println(Arrays.toString(al.toArray()));

		// print each with lambda
		al.forEach(System.out::println);
	}

	static void autoboxingTest(){
		Integer y = new Integer(567);    // make it
	    y++;                             // unwrap it, increment it,
	                                     // rewrap it
	    System.out.println("y = " + y);  // print it
	}

	static void stackTest(){
		Deque<String> deq = new ArrayDeque<>(2);
		deq.addFirst("one");
		deq.addFirst("two");
		deq.addFirst("three"); // Line 9

		System.out.print(deq.pollLast());
		System.out.print(deq.pollLast());
		System.out.print(deq.pollLast()); // Line 12
	}
}