package algorithm.leetCode;

/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.
The same repeated number may be chosen from candidates unlimited number of times.

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

### Example
Input: candidates = [2,3,6,7], target = 7,
A solution set is: [  [7],  [2,2,3] ]

Input: candidates = [2,3,5], target = 8,
A solution set is: [  [2,2,2,2],  [2,3,3],   [3,5] ]

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Backtrack_Array_39 {

    public static void main(String... args) {
        int[] candidates = {1, 1, 2};

        List<List<Integer>> results = combinationSum(candidates, 7);
        for (List<Integer> result : results) {
            System.out.println(Arrays.toString(result.toArray()));
        }
        // [  [7],  [2,2,3] ]

    }

    // Backtrack
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        return null;
    }

}