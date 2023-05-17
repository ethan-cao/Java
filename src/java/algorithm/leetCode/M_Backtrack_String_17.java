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
        StringBuilder tracker = new StringBuilder();

        if (digits.isEmpty()) {
            return combinations;
        }

        collect(combinations, tracker, digits, 0);

        return combinations;
    }

    private void collect(List<String> combinations, StringBuilder tracker, String digits, int start) {
        if (start >= digits.length()) {
            combinations.add(tracker.toString());
            return;
        }

        char digit = digits.charAt(start);
        String letters = letterMap[digit - '2'];

        for (int i = 0; i < letters.length(); ++i) {
            char letter = letters.charAt(i);

            tracker.append(letter);

            collect(combinations, tracker, digits, start + 1);

            tracker.deleteCharAt(tracker.length() - 1);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS, Queue
    // Time: O(N^3), 6ms
    public static List<String> letterCombinations1(String digits) {
        // use LinkedList for FIFO queue
        List<String> letterCombinations = new LinkedList<>();

        if (digits == null || digits.isEmpty()) {
            return letterCombinations;
        }

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