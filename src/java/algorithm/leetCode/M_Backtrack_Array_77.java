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

    public static void main(String... args) {
        List<List<Integer>> combinations = combine(4, 2);
        System.out.println(combinations);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();

        collectCombinations(1, n, combination, k, combinations);

        return combinations;
    }

    // fast 1ms
    private static void collectCombinations(int start, int end, List<Integer> combination, int size, List<List<Integer>> combinations) {
        if (size == 0) {
            combinations.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = start; i <= end - size + 1; ++i) {    // 1ms, need to pick (size)个 elements from start, do it when we have enough elements
//        for (int i = start; i <= end; ++i) {             // 17ms
            combination.add(i);
            collectCombinations(i + 1, end, combination, size - 1, combinations);
            combination.remove(combination.size() - 1);
        }
    }

}