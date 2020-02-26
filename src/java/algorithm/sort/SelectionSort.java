package algorithm.sort;

import java.util.Arrays;

/**
 * Similar to insertion sort
 *
 * When examine i-th element, it looks forward to ensure i-th is in sorted position
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] data = {10, 112, 10, 5, 11, 13, 1,3,3,3,3333};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    // Time: O(N^2)
    public static void sort(int[] data) {
        for (int i = 0; i < data.length; ++i) {
             // assume data[i] is the smallest element
             // each iteration is to make sure the smallest one ends in position

            // search for index of smallest element starting from i + 1
            for (int j = i + 1; j < data.length; ++j) {

                // if there is smaller one, exchange value and update
                if (data[j] < data[i]) {
                    int swap = data[j];
                    data[j] = data[i];
                    data[i] =  swap;
                }
            }
        }
    }
}
