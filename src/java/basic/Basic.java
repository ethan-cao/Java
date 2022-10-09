//checked
package basic;

import collection.ArrayListTest;

import static java.util.Arrays.asList;

public class Basic extends ArrayListTest {
	String str;
	static String str1 = "str1";
	String ￥23 = "true";   // currency character can be used
	int instantVariable1, instantVariable2 = 0; // this is is legal

	public static void main(String... args) {
	    // Arbitrary Number of Arguments, zero or more String objects (or an array of them)
		// even if args is 0 or 1 param, it works
		for (String s : args){
			System.out.println(s);
		}


		// this, super cannot be used in static method
		
		int a, b1=3, c=0; // this is legal
		
		int ii = 1_000;
		System.out.println("ii with _ : " + ii);
		
		String str3 = null; // instance variable need to be initialized, otherwise compilation fails
		System.out.println(str3 + 'a');  // null
		
		Basic b = new Basic();
		b.testMethod();
		
		// non static method cannot be invoked in static method. must use b.testMethod2()
		b.testMethod2();
		
		// static method can be used directly in static method
		testMethod3();
		
		// instance variable must be accessed with instance reference
		System.out.println(b.str);
		
		// static field can be used directly in static method
		System.out.println(str1);

		asList("one");  //this is right, as import static java.util.Arrays.asList;
//		Arrays.asList("one"); // this is wrong, as we did not import java.util.Arrays;
	}
	
	void testMethod() {
		// non static method can be invoked directly in non static method
		//Java implicitly assumes a reference to the current object for methods called like this.
		testMethod2();  // is exactly the same as     this.testMethod2();
		
		// static method can be used directly in static method
		testMethod3();
		
		// when local variable has the same name as an instance variable, use this to access to the instance variable
		// if local variable or method parameter has the same name as instance variable, inside the method, the instance variable will be hiden (variable shadowing) 
		System.out.println(str);
		System.out.println(str1);
    }

    public Basic(){
		testMethod2();
		testMethod3();  
		System.out.println(str);    // the same as this.str;
		System.out.println(str1);
	}
	
    public void testMethod2() {
        System.out.println("testMethod2");
    }
    
    public static void testMethod3() {
        System.out.println("testMethod3");
    }
}
