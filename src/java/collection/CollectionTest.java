package collection;

import basic.AccessModifierTest;

import java.util.Arrays;

public class CollectionTest extends ArrayListTest{

    public static void main(String[] args){
        // Within the same package, protected member i is always accessible
        ArrayListTest alt = new ArrayListTest();
        System.out.println(alt.i);
        AccessModifierTest amt = new AccessModifierTest();
        System.out.println(amt.i);
        CollectionTest ct = new CollectionTest();
        System.out.println(ct.i);
    }

    static void binarySearchTest(){
        Object[] array1 = new Object[3];

        array1[0] = "foo";
        array1[1] = 1;
        array1[2] = 'a';

        // Each element is used internally with  element.compare(target)
        // Thus, there is runtime exception
        int index = Arrays.binarySearch(array1, "bar");
        System.out.println(index);

    }
}
