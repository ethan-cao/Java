package algorithm.leetCode;

/*
Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

### Example
Input: nums = [1,2,3]
Output: [ [3], [1],  [2],  [1,2,3],  [1,3],  [2,3],  [1,2],  [] ]

]*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Backtrack_Array_78 {

    public static void main(String... args) {
        int[] nums = {1, 2, 3};

        List<List<Integer>> subsets = subsets2(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
        // [ [], [3], [2],  [1], [1,3],  [2,3],  [1,2], [1,2,3],  ]

    }

    // Backtrack
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        getSubset(nums, subsets, new ArrayList<>(), 0);

        return subsets;
    }

    private static void getSubset(int[] nums, List<List<Integer>> subsets, List<Integer> subset, int start) {
        subsets.add(new ArrayList<>(subset));

        for (int i = start; i < nums.length; ++i) {
            subset.add(nums[i]);
            getSubset(nums, subsets, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

    // Bit manipulation
    // https://leetcode.com/problems/subsets/discuss/27288/My-solution-using-bit-manipulation/26402
    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        int size = 1 << nums.length;

        for (int i = 0; i < size; ++i) {
            List<Integer> subset = new ArrayList<>();

            for (int j = 0; j < nums.length; ++j) {
                int bitmask = 1 << j; // use bitmask to indicate the j-th element is needed

                if ((bitmask & i) != 0) {
                    subset.add(nums[j]);
                }
            }

            subsets.add(subset);
        }

        return subsets;
    }

    // Iterative, BFS
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            int size = subsets.size();

            for (int j = 0; j < size; j++) {
                List<Integer> subset = new ArrayList<>(subsets.get(j));
                subset.add(nums[i]);
                subsets.add(subset);
            }
        }
        return subsets;
    }
}