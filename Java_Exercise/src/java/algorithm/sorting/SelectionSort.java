package algorithm.sorting;

import java.util.Arrays;

/**
 * Does not depend on the initial order
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1};
        int l = data.length;

        for (int i = 0; i < l - 1; ++i) {
            int minPos = i;

            // Find the location of the minimal element
            for (int j = i + 1; j < l -1; ++j) {
                if (data[j] < data[minPos]) {
                    minPos = j;
                }
            }

            if (minPos != i) {
                int temp = data[i];
                data[i] = data[minPos];
                data[minPos] = temp;
            }
        }

        System.out.println(Arrays.toString(data));
    }
}
