import java.util.*;

public class Test {

    public static void main(String... args) {
        int[] data = {2, 2, 3, 5, 13};

        int max = Arrays.stream(data).max().getAsInt();
        int min = Arrays.stream(data).min().getAsInt();
        sort(data, 13);

        System.out.println(Arrays.toString(data));
    }

    private static void sort(int[] data, int range) {
        int[] tally = new int[range + 1];

        for (int datum : data) {
            tally[datum]++;
        }

        for (int i = 1; i < range + 1; ++i) {

        }
    }

}