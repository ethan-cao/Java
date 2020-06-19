package algorithm.sort;

import algorithm.math.Shuffle;

import java.util.Arrays;

/**
 * QuickSort (partition-exchange sort),  a divide and conquer algorithm
 *
 * 1) pick an element partitionKey(pivot) from the array,
 *
 * 2) put elements that are smaller than partitionKey before partitionKey
 * elements that are larger than partitionKey after partitionKey
 * elements that are equal to partitionKey to partitionKey's either side
 * Then partitionKey is in the right position
 *
 * 3) repeat step 1 and 2 to sub-array before and after partitionKey
 *
 * Choice of partitionKey:
 * when 1st element as partitionKey, the more sorted elements there is, the poorer the performance it is
 * solution : randomized the array or pick a random partition key
 */

public class QuickSort {
    private static final int CUTOFF = 5;

    public static void main(String[] args) {
        int[] data = new int[]{2, 2, 333, 333, 1, 442, 3, 122, 2, 21, 2, 333, 2, 1, 3};
//        int[] data = new int[]{3, 2, 1, 5, 6, 4};
//        int[] data = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};

        sort(data);
//        sortWith3Partition(data, 0, data.length - 1);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] array) {
        // shuffling is needed for performance guarantee
//        Shuffle.knuthShuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int low, int high) {
        // optimization: it costs more on sorting small array, using insertion for small array instead
//        if (low + CUTOFF - 1 >= high) {
//            InsertionSort.sort(array);
//            return;
//        }

        if (low >= high) {
            return;
        }

        int partitionKeyIndex = partition(array, low, high);

        sort(array, low, partitionKeyIndex - 1);
        sort(array, partitionKeyIndex + 1, high);
    }

    public static int partition(int[] data, int start, int end) {
        int partitionKeyIdx = start;

        // examine all include start and end
        while (start <= end) {
            // find one from start, that is > data[partitionIdxKey]
            while (start <= end && data[start] <= data[partitionKeyIdx]) {
                start++;
            }

            // find one from end, that is <= data[partitionIdxKey]
            while (start <= end && data[end] > data[partitionKeyIdx]) {
                end--;
            }

            if (start > end) {
                break;
            }

            // swap
            // the 1st one that is larger than partitionKeyIdx before partitionKeyIdx
            // with
            // the 1st one that is smaller than partitionKeyIdx after partitionKeyIdx
            exchange(data, start, end);
        }

        // data[partitionKeyIdx + 1]...data[end] are all <= data[partitionKeyIdx]
        // data[start] til the rest are all > partitionKeyIdx

        // put partitionKeyIdx to its sorted position
        exchange(data, partitionKeyIdx, end);

        return end;
    }

    // 3-way partitioning QuickSort is most effective when there are lots duplicate elements
    // application : Dutch national flag problem
    public static void sortWith3Partition(int[] data, int low, int high) {
        if (low >= high) {
            return;
        }

        int partitionKey = data[low];

        int lessThanIdx = low; // since we check element that equals to partitionKey, start from 0
        int greaterThanIdx = high;

        int i = low; // scan i from left to right.
        while (i <= greaterThanIdx) {
            if (data[i] < partitionKey) {
                exchange(data, i, lessThanIdx);
                i++;
                lessThanIdx++;
            } else if (data[i] > partitionKey) {
                exchange(data, i, greaterThanIdx);
                greaterThanIdx--;
            } else {
                i++;
            }
        }

        sortWith3Partition(data, low, lessThanIdx - 1);
        sortWith3Partition(data, greaterThanIdx + 1, high);
    }

    private static void exchange(int[] array, int index1, int index2) {
        int swap = array[index1];
        array[index1] = array[index2];
        array[index2] = swap;
    }
}