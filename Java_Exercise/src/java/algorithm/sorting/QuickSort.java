package algorithm.sorting;

import java.util.Arrays;

public class QuickSort {
    private static final int CUTOFF = 5;

    public static void main(String[] args) {
        Integer[] data = {2, 4, 5, 3, 1};
        data = new Integer[]{2, 4, 5, 3, 1, 10, 6, 9, 7,8};


        sort(data);
        System.out.println(Arrays.toString(data));
    }

    private static void sort(Comparable[] array) {
        // shuffling is needed for performance guarantee
        Shuffle.knuthShuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int low, int high) {
        // optimization:  since there is more costs on small array, using insertion for small array sorting
        if (low + CUTOFF - 1 >= high) {
            InsertionSort.sort(array);
            return;
        }

        int partitionKeyIndex = partition(array, low, high);
        sort(array, low, partitionKeyIndex - 1);
        sort(array, partitionKeyIndex + 1, high);
    }

    /**
     * @return index of the partition key
     */
    private static int partition(Comparable[] array, int low, int high) {
        Comparable partitionKey = array[low];
        int i = low + 1;
        int j = high;

        while (true) {
            while (array[i].compareTo(partitionKey) < 0) {
                i++;
                if (i > high) {
                    break;
                }
            }

            while (array[j].compareTo(partitionKey) > 0) {
                j--;
                if (j < low) {
                    break;
                }
            }

            if (j <= i) {
                break;
            }

            exchange(array, i, j);
        }

        exchange(array, low, j);

        return j;
    }

    private static void exchange(Comparable[] array, int index1, int index2) {
        Comparable temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

