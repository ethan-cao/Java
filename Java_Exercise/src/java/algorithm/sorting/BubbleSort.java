package algorithm.sorting;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1};
        int L = data.length;

        for (int i = 0; i < L; ++i) {

            // find the largest element and move it to data[l-i]
            for (int j = 1; j < L - i; ++j) {
                if (data[j - 1] > data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(data));
    }
}
