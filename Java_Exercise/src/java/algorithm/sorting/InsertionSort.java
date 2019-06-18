package algorithm.sorting;

import java.util.Arrays;

/**
 * Depends on the initial order
 * Best case: data in ascending order, Worst case : data in descending order
 */
public class InsertionSort {
    public static void main(String[] args) {
        Integer[] data = {2222, 431, 5, 4, 12, 5, 3, 1};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(Comparable[] data) {
        // Assume left from i, everything is sorted, right part is un-sorted
        int L = data.length;
        for (int i = 0; i < L; ++i) {

            // put the smallest number to the left most
            for (int j = i; j > 0; --j) {

                if (data[j - 1].compareTo(data[j]) > 0) {
                    Comparable temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                } else {
                    break;
                }
            }
        }

    }
}