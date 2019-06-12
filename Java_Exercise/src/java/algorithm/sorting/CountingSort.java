package algorithm.sorting;

import java.util.Arrays;

/**
 * Valid for integer only and need to know the range of our input integers.
 * works better with same rang integers (max-mix)
 */

public class CountingSort {

    public static void main(String[] args) {
        int[] data = {2, 4, 5, 4, 2, 3, 1};

        sort(data, 1, 5);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] array, int smallest, int largest) {
        // count occurrence
        int[] tally = new int[largest - smallest + 1];
        for (int a : array) {
            tally[a - smallest] += 1;
        }

        // after iteration, tally[i] embodies number of elements that are <=  (the element represented by tally[i])
        for (int i = 1; i < tally.length; ++i) {
            tally[i] += tally[i - 1];
        }

        // position element in order
        int[] result = new int[array.length];
        for (int a : array) {
            int numberOfSmallerElements = tally[a - smallest]; // number of elements that are <=  (the element represented by tally[i])
            result[numberOfSmallerElements - 1] = a;  // -1 : since index starts from 0
            tally[a - smallest]--;
        }

        System.arraycopy(result, 0, array, 0, array.length);
    }
}
