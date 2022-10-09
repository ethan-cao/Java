package algorithm.math;

import java.util.*;

public class Shuffle {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        knuthShuffle(data);

        System.out.println(Arrays.toString(data));
    }

    private static final int SEED = 1110;
    private static final Random random = new Random(SEED);

    // linear time shuffling algorithm, used to randomize the sequence
    public static void knuthShuffle(int[] data) {
        for (int i = 1; i < data.length; ++i) {
            int j = random.nextInt(i);

            if (i == j){
                continue;
            }

            int swap = data[i];
            data[i] = data[j];
            data[j] = swap;
        }
    }
}