package algorithm.sorting;

import java.util.Arrays;

/**
 * Depends on the initial order
 * Best case: data in ascending order, Worst case : data in descending order
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] a = {2, 4, 5, 3, 1};
        int l = a.length;

        for (int i = 1; i < l; ++i) {  // Assume the 1st element is sorted
            int x = a[i];     // start checking from the 2nd element

            int j = i - 1;    // compare elements before i
            while (j >= 0 && a[j] > x) {  // look for correct position for a[i]
                a[j+1] = a[j];
                --j;
            }

            a[j + 1] = x;  // put a[i] to the right position
        }

        System.out.println(Arrays.toString(a));
    }
}