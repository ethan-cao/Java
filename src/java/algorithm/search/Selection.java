package algorithm.search;

import algorithm.math.Shuffle;
import algorithm.sort.QuickSort;

public class Selection {

    public static void main(String[] args) {
        Comparable k_th_smallest = QuickSelect1(new int[]{32, 43, 555, 33, 1}, 2);

        System.out.println(k_th_smallest);
    }

    // Time: O(N)
    // Quick Select, based on quickSort,
    // Given an data of N items, find a targetIdx-th largest item
    public static Comparable QuickSelect1(int[] data, int idx) {
        Shuffle.knuthShuffle(data);

        int targetIdx = data.length - idx;
        int start = 0;
        int end = data.length - 1;

        while (start < end) {
            int partitionKeyIndex = QuickSort.partition(data, start, end);

            if (partitionKeyIndex > targetIdx) {
                end = partitionKeyIndex - 1;
            } else if (partitionKeyIndex < targetIdx) {
                start = partitionKeyIndex + 1;
            } else {
                return data[partitionKeyIndex];
            }
        }

        // now targetIdx == partitionKeyIndex
        return data[targetIdx];
    }

}
