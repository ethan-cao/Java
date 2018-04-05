// checked
package basic;

public class Polymorphism {
    public static void main(String[] args) {
        // Polymorphism applies to overridden instance method
        TestA v1 = new TestA();
        TestA v2 = new TestB();

        // Polymorphism, Object type determines which overridden method is used at runtime
        v1.method();
        v2.method();

        // method1 is static method, polymorphism does not work
        // static method is decided by the reference type
        v1.method1();
        v2.method1();

        // Reference variable's type determines which methods are accessible
//         v2.method2();
//         v2.i;

        // Reference type casting, the method to be called depends on the actual type of the object
        TestA a = new TestB();
        TestB b = (TestB) a;
        b.method2();
        // equals to
        ((TestB) a).method2();

        // reference type cast, (new TestB()) this is also reference, similar to above
        //	The casts do nothing to alter the runtime behaviour.
        (new TestB()).start();
        ((TestA) new TestB()).start();


        // not polymorphism, Hiding Fields, two s1 instance variables exist in TestA and TestB, respectively
        System.out.println("s1 using TestB reference: " + (new TestB()).s1);
        System.out.println("s1 using TestA reference: " + ((TestA) new TestB()).s1);

        // not polymorphism, variable access depends on the reference type
        System.out.println("s1 using TestA reference: " + v1.s1);
        System.out.println("s1 using TestA reference: " + v2.s1);  // not B

        Event obj1 = new Snooker();
        CueSports obj2 = new Snooker();
        System.out.println("Event reference : " + obj1.getCategory());
        System.out.println("CusSport reference : " + obj2.getCategory());
    }
}

class TestA {
    public String s1 = "A";

    public void start() {
        System.out.println("start in TestA");
    }

    public void method() {
        System.out.println("method in TestA");
    }

    public static void method1() {
        System.out.println("method1 in TestA");
    }

    public void method3() {
        System.out.println("method3 in TestA");
    }

    public void m() {
        System.out.println(s1);
    }
}

class TestB extends TestA {
    /**
     * instance variables CANNOT be overridden, this is not override
     * When declare a instance variable with the same name as an existing field in a superclass,
     * the new field hides the existing field.
     * The one from superclass is still present in the subclass, and can be used by casting the reference type
     */
    public String s1 = "B";
    public int i;

    @Override
    public void start() {
        System.out.println("start in TestB");
    }

    @Override
    public void method() {
        System.out.println("method in TestB");
    }

    public static void method1() {
        System.out.println("method1 in TestB");
    }

    public void method2() {
        System.out.println("method2 in TestB");
    }

    public void method3(int i) {
        System.out.println("method3 in TestA");
    } // overload
}


interface Event {
    String getCategory();
}

class CueSports {
    public String getCategory() {
        return "Cue sports";
    }
}

class Snooker extends CueSports implements Event {
}