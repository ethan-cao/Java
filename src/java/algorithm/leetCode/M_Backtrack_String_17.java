package algorithm.leetCode;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters is given below. Note that 1 does not map to any letters.
2 : a, b, c
3 : d, e, f
4 : g, h, i
5 : j, k, l
6 : m, n, o
7 : p, q, r, s
8 : t, u, v
9 : w, x, y, z

### Example
"23" -> ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Q22 is similar

*/

import java.util.*;

public class M_Backtrack_String_17 {

    public static void main(String... args) {
        System.out.println(letterCombinations1("2")); // [a, b, c]
        System.out.println(letterCombinations1("3")); // [d, e, f]

        System.out.println(letterCombinations1("22")); // ["aa","ab","ac","ba","bb","bc","ca","cb","cc"]
        System.out.println(letterCombinations1("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(letterCombinations1("33")); // ["dd","de","df","ed","ee","ef","fd","fe","ff"]

        System.out.println(letterCombinations1("222"));
        // ["aaa","aab","aac","aba","abb","abc","aca","acb","acc","baa","bab","bac","bba","bbb","bbc","bca","bcb","bcc","caa","cab","cac","cba","cbb","cbc","cca","ccb","ccc"]
    }

    // Backtracking, Recursion, DFS
    // Time: 0ms
    private static String[] letterMap = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();

        if (digits == null || digits.isEmpty()) {
            return combinations;
        }

        StringBuilder sb = new StringBuilder("");

        return getCombinations(combinations, sb, digits, 0);
    }

    private List<String> getCombinations(List<String> combinations, StringBuilder combination, String digits, int idx) {
        if (idx >= digits.length()) {
            combinations.add(combination.toString());
            return combinations;
        }

        int letterMapIdx = digits.charAt(idx) - '2';
        String letters = letterMap[letterMapIdx];

        for (char letter : letters.toCharArray()) {
            combination.append(letter);

            getCombinations(combinations, combination, digits, idx + 1);

            // backtrack
            combination.deleteCharAt(combination.length() - 1);
        }

        return combinations;
    }

    // BFS, Queue
    // Time: O(N^3), 6ms
    public static List<String> letterCombinations1(String digits) {
        // use LinkedList for FIFO queue
        List<String> letterCombinations = new LinkedList<>();

        if (digits == null || digits.isEmpty()) {
            return letterCombinations;
        }

        String[] letterMap = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

        letterCombinations.add("");

        for (char digit : digits.toCharArray()) {
            int index = digit - '2';
            String letters = letterMap[index];

            int size = letterCombinations.size();
            for (int i = 0; i < size; ++i) {
                // FIFO queue, all combinaiton at a level, BFS
                String combination = letterCombinations.remove(0);

                for (char letter : letters.toCharArray()) {
                    letterCombinations.add(combination + letter);
                }
            }
        }

        return letterCombinations;
    }

}