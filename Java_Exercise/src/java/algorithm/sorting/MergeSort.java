package algorithm.sorting;

import java.util.Arrays;

/**
 * Time complexity :  NlogN
 *
 * Merge sort is too complicated for tiny array, use insertion sort instead
 * Optimal per compare (upper bound and lower bound are the same), not optimal per space usage
 *
 * https://youtu.be/fHYVhCL4riM
 *
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] data = {4, 2, 5, 3, 6, 1};
        int L = data.length;

        int[] aux = new int[L];  // auxiliary array
        sort(data, aux, 0, L - 1);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data, int[] aux, int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2; // constant time

        sort(data, aux, low, middle);
        sort(data, aux, middle + 1, high);

        // optimization
        // no need to merge if the largest one in 1st array is less than the smallest one in 2nd array
        if (data[middle] < data[middle + 1]) {
            return;
        }

        merge(data, aux, low, middle, high);
    }

    // runs in linear time
    public static void merge(int[] data, int[] aux, int low, int middle, int high) {
        // data[low - middle]  and  data[middle+1 - high]    should be sorted already
        // now need to merge data[low - middle] and data[middle+1 - high]

        System.arraycopy(data, low, aux, low, high - low + 1);

        int idx1 = low;         // staring index for data[low - middle]
        int idx2 = middle + 1;  // staring index for data[middle+1 - high]

        // sort data[low - high]
        for (int i = low; i <= high; ++i) {
            if (idx1 > middle) { // if idx1 reaches the middle
                data[i] = aux[idx2++];
            } else if (idx2 > high) { // if idx2 reaches the end
                data[i] = aux[idx1++];
            } else if (aux[idx1] < aux[idx2]) {
                data[i] = aux[idx1++];
            } else {
                data[i] = aux[idx2++];
            }
        }

        // data[low - high] is sorted
    }

}