package basic;

import java.util.Arrays;
import java.util.List;

public class MethodTest {

    static void staticMethod(){
    }

    void instanceMethod(){
    }

    public static void main(String[] args){
        int[] i = {1};

        m1(1);
        m1(i);

//        m2(1); // compilation fail
        m2(i);
    }

    // accepts array or an arbitrary number of values
    static void m1(int... o){
        System.out.println(o);
    }

    // Only accepts array
    static void m2(int[] o){
        System.out.println(o);
    }

    public static void methodReference(){
        List l = Arrays.asList("A", "B", "C");

        // equivalent
        l.forEach(s -> System.out.println(s)); // lambda
        l.forEach(System.out::println);  // method reference

        // a method reference can only be used to replace a single-method lambda expression.


        // a functional interface, an interface with just one abstract method.
    }

    public Thread m3(){
        return null;   // regardless the return type, return null is always ok
    }
}
