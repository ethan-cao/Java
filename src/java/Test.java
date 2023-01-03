import java.util.*;

public class Test {
    public static void main(String[] args) {

        int amount = 3;
        int[] coins = new int[] { 1, 2, 5 };

        int L = coins.length;

        int[] counts = new int[amount + 1];

        // Arrays.fill(counts, Integer.MAX_VALUE);
        // counts[0] = 0;

        for (int coinIdx = 0; coinIdx < L; ++coinIdx) {
            int coinValue = coins[coinIdx];

            for (int value = 1; value <= amount; ++value) {
                int skip = value == 1 ? Integer.MAX_VALUE : counts[value];

                int take = value - coinValue >= 0 && counts[value - coinValue] != Integer.MAX_VALUE
                        ? 1 + counts[value - coinValue]
                        : Integer.MAX_VALUE;

                counts[value] = Math.min(skip, take);
            }
        }

        int r = counts[amount] == Integer.MAX_VALUE ? -1 : counts[amount];

        System.out.println(r);
    }
}