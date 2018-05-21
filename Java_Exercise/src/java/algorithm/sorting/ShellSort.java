package algorithm.sorting;

import java.util.Arrays;

/**
 * Based on h-sort : insertion sort with stride length h.
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1, 13, 10, 6, 9, 8, 7, 11, 12};
        int L = data.length;

        // gap sequence by Knuth : 3X + 1 -- 1, 4, 13, 40, 121, 364
        int h = 1;
        while (h < L / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // insertion sort
            for (int i = h; i < L; ++i) {
                for (int j = i; j >= h; j -= h) {
                    if (data[j] < data[j - h]) {
                        int temp = data[j - h];
                        data[j - h] = data[j];
                        data[j] = temp;
                    }
                }
            }
            h = h / 3;
        }

        System.out.println(Arrays.toString(data));
    }
}
