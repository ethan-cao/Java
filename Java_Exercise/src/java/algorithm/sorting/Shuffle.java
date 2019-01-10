package algorithm.sorting;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.Random;

public class Shuffle {
    public static void main(String[] args) {
        Integer[] data = {1, 3, 8, 10, 17};

        knuthShuffle(data);

        System.out.println(Arrays.toString(data));
    }

    // linear time shuffling algorithm
    public static void knuthShuffle(Object[] array) {
        int L = array.length;

        final int seed = 110;
        Random random = new Random(seed);

        for (int i = 1; i < L; ++i) {
            int j = random.nextInt(i);

            if (i == j){
                continue;
            }

            Object temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

    }
}
