package algorithm.sorting;

import java.util.Arrays;

/**
 * Depends on the initial order
 * Best case: data in ascending order, Worst case : data in descending order
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1};
        int l = data.length;

        // Assume left from i, everything is sorted, right part is un-sorted
        for (int i = 0; i < l; ++i) {

            // put the smallest number to the beginning
            for (int j = i; j > 0; --j) {

                if (data[j - 1] > data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                } else {
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(data));
    }
}