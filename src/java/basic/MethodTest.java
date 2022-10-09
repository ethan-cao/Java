package basic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class ParentMethodTest{
    static void staticMethod(String s){
        System.out.println(s);
    }
}

public class MethodTest extends ParentMethodTest {
    MethodTest() {
    }

    MethodTest(String s) {
        System.out.println(s);
    }

    static void staticMethod(String s){
        System.out.println(s);
    }

    void instanceMethod(String s){
        System.out.println(s);
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
        List<String> list = Arrays.asList("A", "B", "C");

        // equivalent
        // a method reference can only be used to replace a single-method lambda expression.
        list.forEach(s -> System.out.println(s)); // lambda
        list.forEach(System.out::println);  // method reference

        list.forEach(MethodTest::staticMethod);  // reference static method
        list.forEach(ParentMethodTest::staticMethod);  // reference parent static method or super::staticMethod in instance method
        list.forEach(new MethodTest()::instanceMethod);  // reference instance method
        list.forEach(MethodTest::new);  // reference to constructor

        /* Method references are expressions which have the same treatment as lambda expressions (...),
           but instead of providing a method body, they refer an existing method by name.
         */



    }

}
