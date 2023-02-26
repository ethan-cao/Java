package algorithm.leetCode;

/*
Given a collection of distinct integers, return all possible permutations.

### Example
[1,2,3] -> [ [1,2,3],  [1,3,2],  [2,1,3],  [2,3,1],  [3,1,2],  [3,2,1] ]

*/

import java.util.*;

public class M_Backtrack_Array_46 {

    public static void main(String... args) {
        int[] nums = { 1, 2, 3 };

        List<List<Integer>> subsets = permute(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
        // [ [1,2,3],  [1,3,2],  [2,1,3],  [2,3,1],  [3,1,2],  [3,2,1] ]

    }

    // Backtrack
    // 1ms
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();

        collect(permutations, tracker, nums);

        return permutations;
    }

    private static void collect(List<List<Integer>> permutations, List<Integer> tracker, int[] nums) {
        if (tracker.size() == nums.length) {
            permutations.add(new ArrayList<>(tracker));
            return;
        }

        for (int num : nums) {
            if (tracker.contains(num)) {
                continue;
            }

            tracker.add(num);

            collect(permutations, tracker, nums);

            tracker.remove(tracker.size() - 1);
        }
    }

    // Iterative
    // https://leetcode.com/problems/permutations/discuss/18237/My-AC-simple-iterative-javapython-solution
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        permutations.add(new ArrayList<>());

        for (int i = 0; i < nums.length; ++i) {
            List<List<Integer>> tempPermutations = new ArrayList<>();

            for (int insertPos = 0; insertPos <= i; ++insertPos) {

                for (List<Integer> permutation : permutations) {
                    List<Integer> permutationCopy = new ArrayList<>(permutation);
                    permutationCopy.add(insertPos, nums[i]);
                    tempPermutations.add(permutationCopy);
                }
            }

            permutations = tempPermutations;
        }

        return permutations;
    }

}