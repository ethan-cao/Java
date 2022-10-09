import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4};

        final int L = nums.length;

        int maxSum = nums[0];

        // maxSums[i]: max sum including nums[i]
        int maxSums[] = new int[L];

        // BASE
        maxSums[0] = nums[0];

        for (int i = 1; i < L; ++i) {
            // TRANSFORM


            // must always includes num, this case is subarray including num
            // because maxSums[i-1] always includes the previous num, 
            int appendingArray = maxSums[i - 1] + nums[i];
            int newArray = nums[i];

            maxSums[i] = Math.max(appendingArray, newArray);
            maxSum = Math.max(maxSum, maxSums[i]);
        }

    }
}