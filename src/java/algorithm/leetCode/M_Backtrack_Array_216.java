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
        System.out.println(combinationSum3(3, 9));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();

        collectCombinations(1, 9, k, n, combination, combinations);

        return combinations;
    }

    private static void collectCombinations(int current, int end, int size, int sum, List<Integer> combination, List<List<Integer>> combinations) {
        if (sum == 0 && size == 0) {
            combinations.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = current; i <= end; ++i) {
            combination.add(i);
            collectCombinations(i + 1, end, size - 1, sum - i, combination, combinations);
            combination.remove(combination.size() - 1);
        }
    }

}