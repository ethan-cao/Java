package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class IterationTest {

	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
		// test 1 and test 2 are more efficient than test 3
		// test 1 and test 2 are, performance wise, pretty much the same

		list.add(1);
		list.add(3);
		list.add(5);

		test1();
	}

	/**
	 * shorthand for test 2, applicable for collection implementing Iterable
	 */
	static void test1 (){
		for (Integer integer : list) {
			if (integer.intValue() == 1){
				integer =  2;
			}
		}

		// In the beginning of iteration, a snapshot is used to iterate.
		// So change made during iteration has no impact on original collection
        // this.list is still 1, 3, 4
	}

	// Order relies on iterator
	static void test2 (){
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
		   Integer i = iterator.next();
		}

		// Alternative
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()){
			Integer i = it.next();
		}
	}
	
	static void test3(){
		for(int i = 0; i < list.size(); i++) {
			Integer o = list.get(i);
		}
	}

	// Java 8 forEach, which accepts a java.util.function.Consumer object as argument
	static void test4(){
		list.forEach(new Consumer<Integer>(){
			@Override
			public void accept(Integer i) {
				System.out.println(i);
			}
		});
	}
}
