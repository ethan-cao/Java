package basic;

import collection.ArrayListTest;
import collection.CollectionTest;

public class AccessModifierTest extends ArrayListTest {
    private int nnn = 1;

    public static void main(String[] args) {
        /*
        private member is accessible within the body of a class
        which encloses the declaration of the private member or the constructor
        as defined in Java Language Specification,ref:
        https://stackoverflow.com/questions/17027139/access-private-field-of-another-object-in-same-class
         */
        AccessModifierTest.Person p = new AccessModifierTest().new Person();
        System.out.println(p.account);


        /*
        Outside the package where the protected member i is declared,
        protected member i is accessible only by code that is responsible for the implementation of that object
        Here, we cannot access ArrayListTest.i  nor CollectionTest.i, because AccessModifierTest is not the implementation
        As AccessModifierTest is the implementation of itself, we can access AccessModifierTest.i
        https://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.6.2
         */
        ArrayListTest alt = new ArrayListTest();
//        System.out.println(alt.i);  // not accessible

        CollectionTest ct = new CollectionTest();
//        System.out.println(ct.i);   // not accessible

        AccessModifierTest amt = new AccessModifierTest();
        System.out.println(amt.i);


        Person1 p1 = new Person1();
//        p1.m();  // not accessible
    }

    class Person {
        private String account;
        // if Person is outer class, even if in this same java file , account is not accessible

        void m() {
            new AccessModifierTest().nnn++;
        }
    }

}

class Person1 {
    private void m(){
    }
}