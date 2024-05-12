package algorithm.leetCode;

/*
Given a collection of candidate numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

### Example
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is: [[1,1,6],[1,2,5],[1,7],[2,6]]

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is: [ [1,2,2], [5] ]

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Backtrack_Array_40 {

    public static void main(String... args) {
        System.out.println(combinationSum(new int[] { 8, 8 }, 8)); // [[8]]
        System.out.println(combinationSum(new int[] { 2, 5, 2, 1, 2 }, 5)); // [ [1,2,2], [5] ]
        System.out.println(combinationSum(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8)); // [[1,1,6],[1,2,5],[1,7],[2,6]]
    }

    // Backtrack
    // Time complexity O(2^n). Space complexity O(n)
    // 2ms
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();

        // sort array in ascending order
        // for early termination optimization
        Arrays.sort(candidates);

        collect(combinations, tracker, candidates, 0, target);

        return combinations;
    }

    static void collect(List<List<Integer>> combinations, List<Integer> tracker, int[] candidates, int startIdx,
            int target) {
        if (target == 0) {
            combinations.add(new ArrayList<>(tracker));
            return;
        }

        // early termination optimization
        if (target < 0) {
            return;
        }

        for (int idx = startIdx; idx < candidates.length; ++idx) {
            int candidate = candidates[idx];

            tracker.add(candidate);

            // this is differernt from Q39, idx + 1 since each number can be used only once
            collect(combinations, tracker, candidates, idx + 1, target - candidate);

            tracker.remove(tracker.size() - 1);

            // skip duplicateds
            while (idx + 1 < candidates.length && candidates[idx + 1] == candidate) {
                idx++;
            }
        }
    }

}