package algorithm.sorting;

import java.util.Arrays;

/**
 * like selection sort
 *
 * use a heap data structure rather than a linear-time search to find the maximum
 *
 * Using Binary tree (heap sorted) to sort @see BinaryHeap
 */
public class HeapSort {

    public static void main(String[] args) {
        // !!! keep the 1st empty, because we are using heap-sorting
        Integer[] data = {null, 16, 522, 9, 11, 0, 3, 14, 7, 3};

        sort(data);

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

    private static void sink(Comparable[] data, int k, int N) {
        int leftChildKey = 2 * k;
        int rightChildKey = leftChildKey + 1;

        while (rightChildKey <= N) {
            int largerKey = data[leftChildKey].compareTo(data[rightChildKey]) > 0 ? leftChildKey : rightChildKey;

            if (data[largerKey].compareTo(data[k]) > 0) {
                exchange(data, largerKey, k);
                k = largerKey;

                leftChildKey = 2 * k;
                rightChildKey = leftChildKey + 1;
            } else {
                break;
            }
        }

//        data = Arrays.copyOfRange(data, 1, data.length);
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
