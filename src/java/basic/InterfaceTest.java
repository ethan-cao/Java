// checked
package basic;

public class InterfaceTest {
    public static void main(String[] args) {
    }
}

interface I1 {
    int i = 0; // field is public static final implicitly , must assign value
               // only public static final are permitted

    public A m1(); // method is public abstract implicitly
                   // only public abstract are permitted
}

interface I2 extends I1 {
    @Override
    public B m1();
}

interface I3 {
    void method1(); // method is public abstract implicitly
}

interface I4 extends I2, I3 {

    // Java 8, providing default implementation
    default void log(){
        System.out.println("log");
    }

    // not allowed to have Object class default methods.
    // default String toString(){}

    // Java 8
    static void sayHelloToWorld() {
        System.out.println("Hello, world!");
    }
}

abstract class CLS implements I2, I3, I4 {
    @Override
    public B m1() {
        return null;
    }

    // CLS does not implement method1, as it is abstract, it is ok
}

class CLS1  extends CLS implements I1 { // the order maters
    // as CLS does not implement method1 and CLS1 is not abstract, method1 must be implemented
    // only need to implement once, although it is declared in I1 & I3
    @Override
    public void method1() {
    }

//  this causes compiler error, as method1() was public implicitly
//	void method1(){}
}

