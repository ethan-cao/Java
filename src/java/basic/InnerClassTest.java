package basic;

public class InnerClassTest {

	public static void main(String[] args) {
		// regular inner class
		MyOuter outer = new MyOuter();
		MyOuter.MyInner inner = outer.new MyInner();
		// Equivalent
		// MyOuter.MyInner inner = new MyOuter().new MyInner();

		inner.seeOuter();


		Food f = new Food();
		f.m();

		new TestClass().m();   
	}
}

//================================================================
// Regular Inner Class
class MyOuter {
	private int x = 7;
	static int y = 2;

	public void makeInner() {
		MyInner in = new MyInner();
		in.seeOuter();
	}

	// it is ok to extend outer class
	class MyInner extends MyOuter implements EnumTest.I{

		void seeOuter() {
			System.out.println("Outer x is " + x);
			System.out.println("Outer y is " + y);
			System.out.println("Inner object ref is " + this);
			System.out.println("Outer object ref is " + MyOuter.this);
		}

		@Override
		public void m() {

		}
	}
}

//================================================================
// Method-Local Inner Class
class MyOuter2 {
	private String x = "Outer2";
	static String name = "name";
	static int i =1;

	static void m(){
		class R{
			void m(){
				i++;
			}
		};
		R r = new R();
	}

	void doStuff(final int p) {
		final String z = "Local variable";

		class MyInner {
		    int a = 1;
		    private int b = 2;

		 	void seeOuter() {
				System.out.println("Outer x is " + x);
				System.out.println("Outer name is " + name);
				System.out.println("method parmeter p is " + p); // without final, won't compile.
				System.out.println("Local var z is " + z);       // without final, won't compile.
                i++;
			}
		}

		MyInner in = new MyInner(); // This line must come after the class definition
		in.seeOuter();
	}
}

//================================================================
// (Plain-Old) Anonymous Inner Classes
// If Popcorn is interface, then the new anonymous class would be an implementer of the interface
class Popcorn {
	public void pop() {
		System.out.println("popcorn");
	}
}

class Food {
    int i = 1;
	static int j = 2;
	/*
	 * Declare a reference p of type Popcorn. 
	 * Then declare a anonymous class as a subclass of Popcorn.
	 * p refers to an instance of an anonymous (unnamed) subclass of Popcorn.
	 * p refers to a brand-new instance of a brand-new, just-in-time, anonymous (no name) subclass of Popcorn.
	 */
	Popcorn p = new Popcorn() {  // the Popcorn() constructor is invoked 
		@Override
		public void pop() {
			i++;
			j++;
			System.out.println("anonymous popcorn");
			hi();
		}

		private int a = 1;

		public void hi(){
			System.out.println("hi");
		}
	};

	InnerClassTest instance1 = new InnerClassTest();
	final InnerClassTest instance2 = new InnerClassTest();

	public void m(){
		p.pop();
		// it invokes implementation in the anonymous class
		// p cannot access to hi(), hi() can only be accessed from inside the anonymous class 
		// Because of polymorphism, access is determined by reference type

		InnerClassTest localInstance1 = new InnerClassTest();
		final InnerClassTest localInstance2 = new InnerClassTest();

//		localInstance1 = new InnerClassTest();

		Popcorn p1 = new Popcorn(){
			// Method Local Anonymous Inner Class

			@Override
			public void pop(){
				System.out.println("Popcorn p1");

				// Local classes can definitely reference instance variables
				System.out.println(instance1);
				System.out.println(instance2);

				/**
				 * Before Java 7, only final local variable can be used in anonymous class
				 * From Java 8, final keyword is not mandatory, the local variable only need to be effectively final
                 * A variable whose value is never changed after it is initialized is effectively final.
				 * So if localInstance1 = new InnerClassTest(); is uncommented, compiler will complain
				 */
				System.out.println(localInstance1);
				System.out.println(localInstance2);
			}
		};

		p1.pop();
	}
}

//================================================================
// Argument-defined Anonymous Inner Classes
class MyWonderfulClass {
	void go() {
		Bar b = new Bar();

		b.doStuff(new Foo() {
			public void foof() {
				System.out.println("foof");
			}
		});
		
		// we define the anonymous class and instantiate the instance on the fly 
		// and the as the method argument
	}
}

interface Foo {
	void foof();
}

class Bar {
	void doStuff(Foo f) {
	}
}


//================================================================
// Static Nested Class
class BigOuter {
    static class Nested1 {
    	void go(){
    		System.out.println("Nested1 go method");
    	}
    }
}

class TestNested{
	static class Nested2{
		static int a = 1;

		void go(){
    		System.out.println("Nested2 go method");
    	}
    	
    	public static void main(String[] args){
    		BigOuter.Nested1 nested1 = new BigOuter.Nested1();
    		nested1.go();

    		Nested2 nested2 = new Nested2();
    		nested2.go();
    	}
	}
}

//================================================================
// inner class shadowing

// this TestClass class is accessible from outside the TestInners
class TestClass { 
	void m() { System.out.println("outer"); } 
}

class TestInners {
	public static void main(String[] args) {
		// Here, TestClass refers to the one outputs middle
		TestClass tc = new TestInners().new TestClass();
	}
	
	void go() {
		// Here, TestClass refers to the one outputs middle
		new TestClass().m();   

		class TestClass { 
			void m() { System.out.println("inner"); } 
		}

		// Here, TestClass refers to the one outputs inner
	}

	class TestClass { 
		void m() { System.out.println("middle"); } 
	}
}