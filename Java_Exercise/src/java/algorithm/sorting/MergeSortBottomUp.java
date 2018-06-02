package algorithm.sorting;

import java.util.Arrays;

/**
 * About 10% slower than mergesort
 */
public class MergeSortBottomUp {
    public static void main(String args[]) {
        int[] data = {2, 4, 5, 3, 6, 1};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    // runs in linear time
    public static void merge(int[] data, int[] aux, int low, int middle, int high) {
        // data[low] to data[middle], data[middle+1] to data[high] should be sorted already
        System.arraycopy(data, low, aux, low, high - low + 1);

        int m = low, n = middle + 1;

        for (int i = low; i <= high; ++i) {

            if (m > middle) { // if m reaches the middle
                data[i] = aux[n++];
            } else if (n > high) { // if n reaches the end
                data[i] = aux[m++];
            } else if (aux[m] < aux[n]) {
                data[i] = aux[m++];
            } else {
                data[i] = aux[n++];
            }
        }

        // data[low] to data[high] should be sorted
    }

    public static void sort(int[] data) {
        final int L = data.length;
        int[] aux = new int[L];  // auxiliary array

        for (int size = 1; size < L; size *= 2) {
            for (int low = 0; low < L - size; low += size + size) {
                merge(data, aux, low, low + size - 1, Math.min(low + size + size - 1, L - 1));
            }
        }
    }
}
