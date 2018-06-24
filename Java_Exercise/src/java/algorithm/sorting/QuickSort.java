package algorithm.sorting;

import java.util.Arrays;

public class QuickSort {
    private static final int CUTOFF = 5;

    public static void main(String[] args) {
        Integer[] data = {2, 4, 5, 3, 1};
//        data = new Integer[]{2, 4, 5, 3, 1, 10, 6, 9, 7, 8};
//        data = new Integer[]{2, 1, 3};

//        sort(data);


        data = new Integer[]{2, 2, 3, 3, 1, 2, 3, 1, 2, 1, 2, 3, 2, 1, 3};
        sortWith3Partition(data, 0, data.length);

        System.out.println(Arrays.toString(data));
    }

    private static void sort(Comparable[] array) {
        // shuffling is needed for performance guarantee
        Shuffle.knuthShuffle(array);
        sort(array, 0, array.length - 1);
    }

    public static void sort(Comparable[] array, int low, int high) {
        // optimization:  since there is more costs on small array, using insertion for small array sorting
//        if (low + CUTOFF - 1 >= high) {
//            InsertionSort.sort(array);
        if (low >= high) {
            return;
        }

        int partitionKeyIndex = partition(array, low, high);
        sort(array, low, partitionKeyIndex - 1);
        sort(array, partitionKeyIndex + 1, high);
    }

    /**
     * @return index of the partition key
     */
    public static int partition(Comparable[] array, int low, int high) {
        Comparable partitionKey = array[low];
        int i = low + 1;
        int j = high;

        while (true) {
            // look for one that is not smaller than partitionKey
            while (array[i].compareTo(partitionKey) < 0) {
                i++;
                if (i > high) {
                    break;
                }
            }

            // look for one that is not larger than partitionKey
            while (array[j].compareTo(partitionKey) > 0) {
                j--;
                if (j < low) {
                    break;
                }
            }

            // if j <= i, then partition finishes
            if (j <= i) {
                break;
            }

            exchange(array, i, j);
        }

        exchange(array, low, j);

        return j;
    }

    // 3-way partitioning with quicksort is most effective when there are lots duplicate elements
    // application : Dutch national flag problem
    private static void sortWith3Partition(Comparable[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int partitionKeyIndex = low;
        int i = low; // Scan i from left to right.
        int lessThan = low;
        int greaterThan = high;

        while (i <= greaterThan) {
            i++;

            if (array[i].compareTo(array[partitionKeyIndex]) < 0) {
                exchange(array, i, partitionKeyIndex);
                lessThan++;
            } else if (array[i].compareTo(array[partitionKeyIndex]) > 0) {
                exchange(array, i, greaterThan);
                greaterThan--;
            } else {
                lessThan++;
            }

        }

        sortWith3Partition(array, low, lessThan - 1);
        sortWith3Partition(array, greaterThan + 1, high);
    }

    private static void exchange(Comparable[] array, int index1, int index2) {
        if (index1 == index2 || index1 >= array.length || index2 >= array.length) {
            return;
        }

        Comparable temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

