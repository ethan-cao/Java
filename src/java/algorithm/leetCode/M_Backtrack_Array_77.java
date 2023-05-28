package algorithm.leetCode;

/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:
Input: n = 4, k = 2
Output:
[ [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4], ]
*/

import java.util.*;

public class M_Backtrack_Array_77 {

    // Backtracke
    // 1ms
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();

        collect(combinations, tracker, 1, n, k);

        return combinations;
    }

    private void collect(List<List<Integer>> combinations, List<Integer> tracker, int start, int end, int k) {
        if (tracker.size() == k) {
            combinations.add(new ArrayList<>(tracker));
            return;
        }

        // combination only cares whether the candidate has been used, if used, proceed to the next one, no need to start over
        for (int num = start; num <= end; ++num) {
            tracker.add(num);

            collect(combinations, tracker, num + 1, end, k);

            tracker.remove(tracker.size() - 1);
        }

    }

}