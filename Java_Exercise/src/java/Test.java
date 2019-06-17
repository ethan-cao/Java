import java.util.*;

public class Test {
    public static void main(String... args) {
        int[] data = {2, 4, 5, 3, 11, 2, 10, 2, 3, 5, 13, 1};

        MSDSort(data);

        System.out.println(Arrays.toString(data));
    }

    static int RADIX = 10;

    static void MSDSort(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();
        int maxDigitLength = (max + "").length();

        for (int i = 0; i < maxDigitLength; ++i) {
            int digitIndicator = (int) Math.pow(10, i);
            sort(data, digitIndicator);
        }
    }

    static void sort(int[] data, int digitIndicator) {
        int[][] bucket = new int[RADIX][data.length];
        int[] tally = new int[RADIX];

        for (int i = 0; i < data.length; ++i) {
            int examiningDigit = (data[i] / digitIndicator) % RADIX;
            bucket[examiningDigit][tally[examiningDigit]] = data[i];
            tally[examiningDigit]++;
        }

        int idx = 0;
        for (int i = 0; i < RADIX; ++i) {
            for (int j = 0; j < tally[i]; ++j) {
                data[idx] = bucket[i][j];
                idx++;
            }
        }

    }

}