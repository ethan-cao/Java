package algorithm.sorting;

import java.util.Arrays;

/**
 * Using Binary tree (heap sorted) to sort @see PriorityQueueTest
 */
public class HeapSort {
    public static void main(String[] args) {
        // !!! keep the 1st empty, because we are using heap-sorting
        Integer[] data = {null, 2, 6, 5, 9, 11, 10, 8, 4, 7, 1, 3};

        HeapSort.sort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(Comparable[] array) {
        int N = array.length - 1; // since the 1st one is empty

        // step 1:  Build binary heap
        // N/2 is the smallest node that has children
        for (int k = N / 2; k >= 1; k--) {
            sink(array, k, N);
        }

        // step 2:  Remove the largest one repeatedly
        for (int i = N; i > 1; --i) {
            exchange(array, 1, i);
            sink(array, 1, --N); // size is shrinking
        }
    }

    private static void sink(Comparable[] array, int k, int N) {
        int leftChildKey = 2 * k;
        int rightChildKey = leftChildKey + 1;

        while (rightChildKey <= N) {
            int largerKey = array[leftChildKey].compareTo(array[rightChildKey]) > 0 ? leftChildKey : rightChildKey;

            if (array[largerKey].compareTo(array[k]) > 0) {
                exchange(array, largerKey, k);
                k = largerKey;

                leftChildKey = 2 * k;
                rightChildKey = leftChildKey + 1;
            } else {
                break;
            }
        }
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
