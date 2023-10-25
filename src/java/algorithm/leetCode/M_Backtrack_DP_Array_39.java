package algorithm.leetCode;

/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number
find all unique combinations in candidates where the candidate numbers sums to target
The same repeated number may be chosen from candidates unlimited number of times.

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

### Example
Input: candidates = [2,3,6,7], target = 7,
A solution set is: [  [7],  [2,2,3] ]

Input: candidates = [2,3,5], target = 8,
A solution set is: [  [2,2,2,2],  [2,3,3],   [3,5] ]

*/

import java.util.*;

public class M_Backtrack_DP_Array_39 {

    public static void main(String... args) {
        int[] candidates = { 2, 3, 6, 7 };

        List<List<Integer>> results = combinationSum1(candidates, 7);
        for (List<Integer> result : results) {
            System.out.println(Arrays.toString(result.toArray()));
        }
        // [ [7], [2,2,3] ]
    }

    // Backtrack, 2ms
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();

        // sort array in ascending order
        // for early termination optimization
        Arrays.sort(candidates);

        collect(combinations, tracker, candidates, 0, target);

        return combinations;
    }

    private void collect(
            List<List<Integer>> combinations,
            List<Integer> tracker,
            int[] candidates,
            int idx,
            int target) {
        if (target == 0) {
            combinations.add(new ArrayList<>(tracker));
            return;
        }

        for (int i = idx; i < candidates.length; ++i) {
            int candidate = candidates[i];

            // early termination optimization
            if (candidate > target) {
                break;
            }

            tracker.add(candidate);

            // why using i, not i+1?
            // since each number can be used for multiple times
            collect(combinations, tracker, candidates, i, target - candidate);

            tracker.remove(tracker.size() - 1);
        }
    }

    // brute force 6ms
    // Time: O(N^3)
    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<List<Integer>>> allCombinations = new ArrayList<>();

        for (int sum = 0; sum <= target; ++sum) {
            List<List<Integer>> combinations = new ArrayList<>();

            for (int candidate : candidates) {
                if (candidate > sum) {
                    break;
                }

                if (candidate == sum) {
                    combinations.add(Arrays.asList(candidate));
                    break;
                }

                int remaining = sum - candidate;
                List<List<Integer>> combinationsForRemaining = allCombinations.get(remaining);

                for (List<Integer> combination : combinationsForRemaining) {

                    // if the candidate > last one, that means it has been used
                    if (candidate > combination.get(combination.size() - 1)) {
                        continue;
                    }

                    // sum in combination is difference, difference + candidate = currentTarget
                    List<Integer> newCombination = new ArrayList<>(combination);
                    newCombination.add(candidate);

                    combinations.add(newCombination);
                }
            }

            allCombinations.add(combinations);
        }

        return allCombinations.get(target);
    }

}