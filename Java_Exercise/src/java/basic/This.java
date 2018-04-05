package basic;


public class This {
	public static void main(String[] args) {
		MyThisTest  mtt = new MyThisTest();
		mtt.frobnicate();
	}
}

class MyThisTest {
	  private int a;

	  public MyThisTest() {
		// this() can only appear as the first sentence in constructor
	    this(42); // calls another constructor MyThisTest(int a), thus this() is not invoked implicitly 
	  }

	  public MyThisTest(int a) {
	    this.a = a; // assigns the value of the parameter a to the instance variable a 
	  }

	  public void frobnicate() {
	    int a = 1;

	    System.out.println(a); // refers to the local variable a
	    System.out.println(this.a); // refers to the field a
	    
	    // the print statement calls the toString method of an object
	    // so  System.out.println(this.toString()) is not necessary   
	    System.out.println(this); // refers to this entire object
	  }

	  public String toString() {
	    return "MyThisTest a=" + a; // refers to the field a
	  }
}