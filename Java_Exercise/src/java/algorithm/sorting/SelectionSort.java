package algorithm.sorting;

import java.util.Arrays;

/**
 *
 * Simple but not efficient
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] data = {10, 112, 10, 5, 11, 13, 1,3,3,3,3333};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        for (int i = 0; i < data.length; ++i) {
            /**
             * assume index i has the smallest element
             * each iteration is to make sure the smallest one ends in position
             */
            int minIdx = i;

            // search for index of smallest element starting from i + 1
            for (int j = i + 1; j < data.length; ++j) {

                // if there is smaller one, exchange value and update
                if (data[j] < data[minIdx]) {
                    int temp = data[j];
                    data[j] = data[minIdx];
                    data[minIdx] = temp;
                }
            }
        }
    }
}
