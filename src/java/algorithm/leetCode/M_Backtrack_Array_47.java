package algorithm.leetCode;

/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

### Example
[1,1,2] -> [  [1,1,2],   [1,2,1],   [2,1,1] ]

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Backtrack_Array_47 {

    public static void main(String... args) {
        int[] nums = {1, 1, 2, 2};

        List<List<Integer>> subsets = permuteUnique1(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
        // [[1,1,2,2], [1,2,1,2], [1,2,2,1],  [2,1,1,2],  [2,1,2,1],  [2,2,1,1]]
    }

    // Backtrack
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);

        collectPermutation(nums, permutations, tracker, used);

        return permutations;
    }

    private static void collectPermutation(int[] nums, List<List<Integer>> permutations, List<Integer> tracker, boolean[] used) {
        if (tracker.size() == nums.length) {
            permutations.add(new ArrayList<>(tracker));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (used[i]) {
                continue;
            }

            // !!! skip duplicated case, require nums to be sorted
            // if the current is the same as the previous one && the previous one is not in use
            // this is the same case as the previous is used
            if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }

            tracker.add(nums[i]);
            used[i] = true;

            collectPermutation(nums, permutations, tracker, used);

            tracker.remove(tracker.size() - 1);
            used[i] = false;
        }
    }

    // Iterative, slower than backtrack
    public static List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        permutations.add(new ArrayList<>());

        for (int i = 0; i < nums.length; ++i) {
            int size = permutations.size();

            // clear all existing permutations, replace with newPermutation
            for (int j = 0; j < size; ++j) {
                // remove() removes the specified one, and shifts all the rest towards left
                List<Integer> permutation = permutations.remove(0);

                for (int insertIdx = 0; insertIdx <= permutation.size(); ++insertIdx) {

                    // for duplicate numbers in a row, only add same number in in front of them.
                    if (insertIdx > 0 && nums[i] == permutation.get(insertIdx - 1)) {
                        // !!! use break, continue does not work
                        // since inserting duplicate value, the ignored rest permutations will appear next round
                        // check case [1,1,2,2]
                        break;
                    }

                    ArrayList<Integer> newPermutation = new ArrayList<>(permutation);
                    // add(idx, ele) adds the specified one, shift all rest towards right
                    newPermutation.add(insertIdx, nums[i]);
                    permutations.add(newPermutation);
                }
            }
        }

        return permutations;
    }

}