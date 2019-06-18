package algorithm.sorting;

import java.util.Arrays;

/**
 * Does not depend on the initial order
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] data = {10, 112, 10, 5, 11, 13, 1};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        for (int i = 0; i < data.length; ++i) {
            int minIndex = i;  // assume index i has the smallest element

            // search for index of smallest element starting from i + 1
            for (int j = i + 1; j < data.length; ++j) {
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
    }
}
