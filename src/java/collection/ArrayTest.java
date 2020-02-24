package collection;

import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, null, 5};

        // shortcut of iteration, so change made is not persist in the original array
        for (Object o : array) {
            System.out.print(o + " ");
        }

        System.out.println();
        System.out.println(Arrays.toString(array));

        boolean contains = Arrays.stream(array).anyMatch(x->x ==2);
        System.out.println("array contains 2 : " + contains);

        String[] values = {"AB","BC","CD","AE"};
        contains = Arrays.stream(values).anyMatch("s"::equals);

        Object[] a1 = new Object[]{null, new Object(), new Object()};

        // compilation error
        // Object[] a2 = new Object[3] { null, new Object(), new Object() } ;

        Object[] a3 = {new Object()};

        Object[] a4 = new Object[4];



    }

}
