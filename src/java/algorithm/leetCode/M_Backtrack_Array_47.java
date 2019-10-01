package algorithm.leetCode;

/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

### Example
Input: [1,1,2]
Output: [  [1,1,2],   [1,2,1],   [2,1,1] ]

]*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Backtrack_Array_47 {

    public static void main(String... args) {
        int[] nums = {1, 1, 2};

        List<List<Integer>> subsets = permuteUnique(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
        // [  [1,1,2],   [1,2,1],   [2,1,1] ]

    }

    // Backtrack
    public static List<List<Integer>> permuteUnique(int[] nums) {

        return null;
    }
}
