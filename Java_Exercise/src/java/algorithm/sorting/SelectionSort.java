package algorithm.sorting;

import java.util.Arrays;

/**
 * Does not depend on the initial order
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1};
        int L = data.length;

        for (int i = 0; i < L; ++i) {
            int minIndex = i;  // assume index i has the smallest element

            // search for index of smallest element starting from i + 1
            for (int j = i + 1; j < L; ++j) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }

            // put smallest element in the position of i
            if (minIndex != i) {
                int temp = data[minIndex];
                data[minIndex] = data[i];
                data[i] = temp;
            }
        }

        System.out.println(Arrays.toString(data));
    }
}
