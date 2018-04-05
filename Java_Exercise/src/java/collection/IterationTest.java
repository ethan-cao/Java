package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterationTest {

	public static void main(String[] args) {
		// test 1 and test 2 are more efficient
		// test 1 and test 2 are, performance wise, pretty much the same
	}

	void test1 (){
		List<Integer>  a = new ArrayList<Integer>();
		for (Integer integer : a) {
		  integer.toString();
		}
	
	}

	// Order relies on iterator
	void test2 (){
		List<Integer>  a = new ArrayList<Integer>();
		Integer integer = null;
		for (Iterator iterator = a.iterator(); iterator.hasNext();) {
		   integer = (Integer) iterator.next();
		   integer.toString();
		}
	}
	
	void test3(){
		List<Integer>  a = new ArrayList<Integer>();
		for(int i = 0; i < a.size(); i++) {
			Integer o = (Integer) a.get(i);
			o.toString();
		}
	}
}
