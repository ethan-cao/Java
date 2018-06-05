package algorithm.sorting;

import java.util.Arrays;

/**
 * too complicated for tiny array, use insertion sort instead
 * Optimal per compare (upper bound and lower bound are the same), not optimal per space usage
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 6, 1};
        int L = data.length;

        int[] aux = new int[L];  // auxiliary array
        sort(data, aux, 0, L - 1);

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

    public static void sort(int[] data, int[] aux, int low, int high) {
        // Recursively invoked
        if (low >= high) {
            return;  // put a break point here
        }

        int middle = low + (high - low) / 2; // constant time

        sort(data, aux, low, middle);
        sort(data, aux, middle + 1, high);

        // optimization
        if (data[middle] < data[middle + 1]) {
            return;
        }

        merge(data, aux, low, middle, high);
    }
}
