package algorithm.sort;

import java.util.Arrays;

/**
 * a divide and conquer algorithm
 *
 * Time:  O(NlogN)
 *
 * Merge sort is too complicated for tiny array, use insertion sort instead
 * Optimal per compare (upper bound and lower bound are the same), not optimal per space usage
 *
 * https://youtu.be/fHYVhCL4riM
 *
 * LeetCode: 986
 */

public class MergeSort {

    public static void main(String[] args) {
        int[] data = {16, 522, 3, 9, 11, 0, 3, 14, 7, 3};

        TopDown.sort(data);
//        BottomUp.sort(data);

        System.out.println(Arrays.toString(data));
    }

    static class TopDown {

        public static void sort(int[] data) {
            sort(data, 0, data.length - 1);
        }

        private static void sort(int[] data, int low, int high) {
            if (low >= high) {
                return;
            }

            int middle = low + (high - low) / 2;

            sort(data, low, middle);
            sort(data, middle + 1, high);

            // now, data[low ... middle] and data[middle+1 ... high] are sorted

            merge(data, low, middle, high);
        }

        // runs in linear time
        private static void merge(int[] data, int low, int middle, int high) {
            // optimization: no need to merge if the largest one in 1st array <= the smallest one in 2nd array
            if (data[middle] <= data[middle + 1]) {
                return;
            }

            // data[low] ... data[middle]  and   data[middle+1] ... data[high] are sorted, need to merge them
            int[] aux = new int[data.length]; // use aux[] to avoid overwriting value while copying
            System.arraycopy(data, low, aux, low, high - low + 1);
            // aux[] could be static member to reduce memory usage

            // sort data[low] ... data[high]
            int idx1 = low;          // start of data[low...middle]
            int idx2 = middle + 1;   // start of data[middle+1, high]

            for (int i = low; i <= high; ++i) {
                if (idx1 > middle) {      // when data[low...middle] is exhausted
                    data[i] = aux[idx2++];
                } else if (idx2 > high) { // when data[middle+1...high] is exhausted
                    data[i] = aux[idx1++];
                } else {
                    data[i] = aux[idx1] < aux[idx2] ? aux[idx1++] : aux[idx2++];
                }
            }
            // data[low] ... data[high] is sorted
        }
    }

    // About 10% slower than topDown
    static class BottomUp {

        public static void sort(int[] data) {
            // The first one to be sorted is size 1 array, then size 2, 4, 8
            for (int size = 1; size < data.length; size *= 2) {
                // size + size : low index each time jumps 2 arrays
                for (int low = 0; low < data.length - size; low += size + size) {
                    // low + size - 1 : the end of the first array
                    // low + size + size - 1 : the end of the second array
                    merge(data, low, low + size - 1, Math.min(low + size + size - 1, data.length - 1));
                }
            }
        }

        // runs in linear time
        private static void merge(int[] data, int low, int middle, int high) {
            // data[low] ... data[middle]  and   data[middle+1] ... data[high] are sorted, need to merge them
            int[] aux = new int[data.length];  // auxiliary array
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

            // data[low] ... data[high] is sorted
        }
    }
}