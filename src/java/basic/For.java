package basic;

public class For {

	public static void main(String[] args) {
	
/*
		for (initialization;  termination;  increment) {
			    statement(s)
		}

		The initialization expression initializes the loop; it's executed once, as the loop begins.
		When the termination expression evaluates to false, the loop terminates.
		The increment expression is invoked after each iteration through the loop; it is perfectly acceptable for this expression to increment or decrement a value.
*/
		
		
		for (int x = 3; x < 20; x++) {
			int y = 2;
		}

		int j = 1;
		while ( j < 3) {
			j++;
			int k = 0;
		}

//		this is wrong as x, y,k are declared in for loop, they are not reachable outside
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(k);

		
		// check labeled loop with continue and break
		// any kind of loop can be labeled 
		// label cannot be used with if
		
		
		// break and continue can only be used within loop

        // foreach iterates array
		int[] a1 = {12, 3, 54, 65};
		for(int i: a1){
			System.out.println(i);
		}

		// foreach iterate string
		String test = "test";
		for (char s : test.toCharArray()) {
			System.out.println(s);
		}

		// check collection.IterationTest for foreach usage in collection

	}

}
