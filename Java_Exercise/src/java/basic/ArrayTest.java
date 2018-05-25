package basic;

import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args){
        Integer[] array = {1,2,3, null, 5};

        // shortcut of iteration, so change made is not persist in the original array
        for(Object o : array){
            System.out.print(o + " ");
        }

        System.out.println();
        System.out.println(Arrays.toString(array));

    }

}
