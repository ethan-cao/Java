package algorithm.leetCode;

/*
Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

### Example
nums = [1,2,3] -> [ [3], [1],  [2],  [1,2,3],  [1,3],  [2,3],  [1,2],  [] ]

*/

import java.util.*;

public class M_Backtrack_BitManipulation_Array_78 {

    public static void main(String... args) {
        int[] nums = { 1, 2, 3 };

        List<List<Integer>> subsets = subsets2(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
        // [ [], [3], [2],  [1], [1,3],  [2,3],  [1,2], [1,2,3],  ]
    }

    // Backtrack
    // Time: 0ms
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();

        collect(subsets, tracker, nums, 0);

        return subsets;
    }

    private void collect(List<List<Integer>> subsets, List<Integer> tracker, int[] nums, int startIdx) {
        subsets.add(new ArrayList<>(tracker));

        // no need
        // if (tracker.size() == nums.length) {
        //     return;
        // }

        for (int i = startIdx; i < nums.length; ++i) {
            tracker.add(nums[i]);
            collect(subsets, tracker, nums, i + 1);
            tracker.remove(tracker.size() - 1);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Iterative, BFS
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        // start from an empty subset, each time add an item to all existing subset
        // []
        // [1]
        // [2] [1,2]
        // [3] [1, 3] [2, 3] [1,2, 3]

        for (int i = 0; i < nums.length; ++i) {
            int size = subsets.size();

            for (int j = 0; j < size; ++j) {
                // duplicate all existing subset
                List<Integer> subset = new ArrayList<>(subsets.get(j));

                // add each item to the duplicated subset
                subset.add(nums[i]);

                // add it back to the subsets
                subsets.add(subset);
            }
        }

        return subsets;
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

}