// checked
package basic;

public class InheritanceTest extends B {

    public InheritanceTest(String s) {
        // class B does not have default constructor, we must invoke a particular one
        super(s);
    }

    public static void main(String[] args) {
        A a = new A("a");
        B b = new B("s");
        A c = new B("c");
        InheritanceTest i = new InheritanceTest("i");

        b.test();

        System.out.println(System.lineSeparator());

        // invocation is taking place at where the method is actually implemented
        System.out.println("instance variable a:");
        System.out.println(" " + a.a);
        System.out.println(" " + a.getA());
        System.out.println(" " + b.a);
        System.out.println(" " + b.getA());
        System.out.println(" " + c.a);    // reference type is A, so output A.a
        System.out.println(" " + c.getA()); // getA() implementation is in B, so output B.a

        System.out.println(System.lineSeparator());

        System.out.println(a.m());
        System.out.println(b.m());
        System.out.println(c.m());

        System.out.println(System.lineSeparator());

        System.out.println("static variable V:");
        System.out.println(A.V);
        System.out.println(B.V);
        System.out.println(InheritanceTest.V);

        System.out.println(System.lineSeparator());

        // private member in superclass still exist in subclass
        System.out.println("private b in superclass: " + new B("sub").getB());
    }
}

class A {
    public static int V = 1;
    public int a = 1;
    private int b = 1;

    // there is no default constructor for A
    public A(String s) {
    }

    public int getA() {
        // in this context, instance variable A.a is take precedence over
        // in subclass, B.a is take precedence ( Hiding fields )
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public void test() {
        System.out.println("This is A");
    }

    public void methodA() {
        System.out.println("This is MethodA in class A");
    }

    private Integer getNumber() {
//	Integer getNumber(){
        return 20;
    }

    public static String m(){
        return "m in A";
    }
}

// B possesses all A's  stuff, including the private, B just cannot access the private
class B extends A {
    public static int V = 2;
    public int a = 2;  // Hiding fields

    public B(String s) {
        // as there is no no-argument constructor in super, sub must call sub's other constructor explicitly
        super(s);
    }

//    @Override
//	public int getA() {
//		return this.a;
//	}

    @Override
    public void test() {
        methodA();
    }

    // This is not overriding, getNumber() in A is private, B does not inherit getNumber() from A
    // Thus, B does not have access to getNumber(), B can have its own getNumber()
    // this getNumber is just a completely new method
    // if getNumber() in A is not private, then compilation fails due to incompatible return type
//	@Override
    public Byte getNumber() {
        return 22;
    }

    public static String m(){
        return "m in B";
    }

    // since m() is definde as static in parent class, it cannot be overridden as instance method
//    public String m(){
//        return "m in B";
//    }
}