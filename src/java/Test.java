import java.util.*;

public class Test {

    public static void main(String[] args) {
        System.out.println("@@@");
        int target = 3;
        int[] nums = new int[] { 1, 2, 3 };

        int[] counts = new int[target + 1];

        counts[0] = 1;

        for (int value = 1; value <= target; ++value) {
            for (int i = 0; i < nums.length; ++i) {
                int num = nums[i];

                if (value >= num) {
                    counts[value] += counts[value - num];
                }
            }
        }

        System.out.println( counts[target]);
    }
}