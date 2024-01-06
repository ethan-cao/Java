package algorithm.leetCode;

/*
Find all possible combinations of k numbers that add up to a number n,
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

All numbers will be positive integers.
The solution set must not contain duplicate combinations.

### Example
k = 3, n = 7  -> [[1,2,4]]
k = 3, n = 9  -> [[1,2,6], [1,3,5], [2,3,4]]

*/

import java.util.*;

public class M_Backtrack_Array_216 {

    public static void main(String... args) {
        System.out.println(combinationSum(3, 9));
    }

    // Time: 0ms
    public static List<List<Integer>> combinationSum(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();

        collect(combinations, tracker, 1, k, n);

        return combinations;
    }

    private static void collect(List<List<Integer>> combinations, List<Integer> tracker, int start, int k, int n) {
        if (n == 0 && tracker.size() == k) {
            combinations.add(new ArrayList<>(tracker));
            return;
        }

        if (tracker.size() >= k) {
            return;
        }

        if (n < 0) {
            return;
        }

        for (int num = start; num <= 9; ++num) {
            tracker.add(num);

            collect(combinations, tracker, num + 1, k, n - num);

            tracker.remove(tracker.size() - 1);
        }

    }

}