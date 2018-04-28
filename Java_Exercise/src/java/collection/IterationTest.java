package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterationTest {

	List<Integer>  a = new ArrayList<>();

	public static void main(String[] args) {
		// test 1 and test 2 are more efficient than test 3
		// test 1 and test 2 are, performance wise, pretty much the same
	}

	void test1 (){
		for (Integer integer : a) {
		  integer.toString();
		}
	}

	// Order relies on iterator
	void test2 (){
		for (Iterator<Integer> iterator = a.iterator(); iterator.hasNext();) {
		   Integer i = iterator.next();
		}

		// Alternative
		Iterator<Integer> it = a.iterator();
		while (it.hasNext()){
			Integer i = it.next();
		}
	}
	
	void test3(){
		for(int i = 0; i < a.size(); i++) {
			Integer o = a.get(i);
		}
	}
}
