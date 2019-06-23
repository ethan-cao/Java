package algorithm.sorting;

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
 *
 * Choice of partitionKey:
 * when 1st element as partitionKey, the more sorted elements, the poorer the performance it is
 * solution : randomized the array or pick a random partition key
 */

public class QuickSort {
    private static final int CUTOFF = 5;

    public static void main(String[] args) {
        Integer[] data = new Integer[]{2, 2, 333, 333, 1, 442, 3, 122, 2, 21, 2, 333, 2, 1, 3};

//        sort(data);
        sortWith3Partition(data, 0, data.length - 1);

        System.out.println(Arrays.toString(data));
    }

    private static void sort(Comparable[] array) {
        // shuffling is needed for performance guarantee
        Shuffle.knuthShuffle(array);
        sort(array, 0, array.length - 1);
    }

    public static void sort(Comparable[] array, int low, int high) {
        // optimization: it costs more on small array, using insertion for small array sorting
        if (low + CUTOFF - 1 >= high) {
            InsertionSort.sort(array);
        }

        if (low >= high) {
            return;
        }

        int partitionKeyIndex = partition(array, low, high);

        sort(array, low, partitionKeyIndex - 1);
        sort(array, partitionKeyIndex + 1, high);
    }

    public static int partition(Comparable[] data, int low, int high) {
        Comparable partitionKey = data[low];

        int i = low + 1;
        int j = high;

        // if j <= i, then getPartitionKey finishes
        while (i < j) {
            // look for one that is not smaller than partitionKey
            while (data[i].compareTo(partitionKey) <= 0) {
                i++;
            }

            // look for one that is not larger than partitionKey
            while (data[j].compareTo(partitionKey) > 0) {
                j--;
            }

            // swap (the 1st element that is larger than partitionKey before partitionKey) with
            //      (the 1st element that is smaller than partitionKey after partitionKey)
            exchange(data, i, j);
        }
        // after this iteration, data[partition+1]...data[j] are all <= partitionKey
        //                       data[j+1]...data[high] are all > partitionKey

        // put partitionKey to its sorted position j
        exchange(data, low, j);

        return j;
    }

    // 3-way partitioning QuickSort is most effective when there are lots duplicate elements
    // application : Dutch national flag problem
    private static void sortWith3Partition(Comparable[] data, int low, int high) {
        if (low >= high) {
            return;
        }

        Comparable partitionKey = data[low];

        int lessThan = low; // since we check element that equals to partitionKey, start from 0
        int greaterThan = high;

        int i = low; // scan i from left to right.
        while (i <= greaterThan) {
            if (data[i].compareTo(partitionKey) < 0) {
                exchange(data, i, lessThan);
                i++;
                lessThan++;
            } else if (data[i].compareTo(partitionKey) > 0) {
                exchange(data, i, greaterThan);
                greaterThan--;
            } else {
                i++;
            }
        }

        sortWith3Partition(data, low, lessThan - 1);
        sortWith3Partition(data, greaterThan + 1, high);
    }

    private static void exchange(Comparable[] array, int index1, int index2) {
        Comparable swap = array[index1];
        array[index1] = array[index2];
        array[index2] = swap;
    }
}