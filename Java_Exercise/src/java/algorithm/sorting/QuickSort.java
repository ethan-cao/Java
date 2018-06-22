package algorithm.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        Integer[] data = {2, 4, 5, 3, 1};
        sort(data);
        System.out.println(Arrays.toString(data));
    }

    private static void sort(Comparable[] array) {
        Shuffle.knuthShuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int low, int high) {
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

