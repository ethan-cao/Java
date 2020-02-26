package algorithm.sort;

import java.util.Arrays;

/**
 * ShellSort is a generalization of insertion sort that allows the exchange of far apart items
 *
 * Given h, make sure every h-th elements are sorted using insertion sort
 * Then reducing h gradually to 1, all elements are sorted
 *
 * choice of h :
 * as long as h contains 1, it guarantees the sorted order
 * too large h or too small order both decrease performance
 */

public class ShellSort {

    public static void main(String[] args) {
        int[] data = {7, 12, 2, 4, 5, 3222, 5, 7, 11, 12};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        // determine gap sequence
        // using Knuth’s sequence : 3X + 1
        // 1, 4, 13, 40, 121, 364
        int h = 1;
        while (h < data.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // insertion sort, make every h-th elements in sorted order
            for (int i = h; i < data.length; ++i) {
                for (int j = i; j - h >= 0; j -= h) {
                    if (data[j] < data[j - h]) {
                        exchange(data, j, j - h);
                    } else {
                        break;
                    }
                }
            }

            // reducing h until 1
            h /= 3;
        }
    }

    private static void exchange(int[] array, int index1, int index2) {
        int swap = array[index1];
        array[index1] = array[index2];
        array[index2] = swap;
    }
}
