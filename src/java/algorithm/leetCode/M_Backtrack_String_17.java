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
        System.out.println(letterCombinations1("2"));  // [a, b, c]
        System.out.println(letterCombinations1("3"));  // [d, e, f]

        System.out.println(letterCombinations1("22")); // ["aa","ab","ac","ba","bb","bc","ca","cb","cc"]
        System.out.println(letterCombinations1("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(letterCombinations1("33")); // ["dd","de","df","ed","ee","ef","fd","fe","ff"]

        System.out.println(letterCombinations1("222"));
        // ["aaa","aab","aac","aba","abb","abc","aca","acb","acc","baa","bab","bac","bba","bbb","bbc","bca","bcb","bcc","caa","cab","cac","cba","cbb","cbc","cca","ccb","ccc"]
    }

    // Iterative solution
    public static List<String> letterCombinations(String digits) {
        List<String> letterCombinations = new LinkedList<>();

        String[] letterMap = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for (char c : digits.toCharArray()) {
            String letters = letterMap[c - '2'];

            if (letterCombinations.isEmpty()) {
                letterCombinations.add("");
            }

            List<String> tempLetterCombinations = new LinkedList<>();

            // when using iterator, it iterates on snapshot, avoid modification on the collection
            for (char letter : letters.toCharArray()) {
                for (String combination : letterCombinations) {
                    tempLetterCombinations.add(combination + letter);
                }
            }

            letterCombinations = tempLetterCombinations;
        }

        return letterCombinations;
    }

    // Backtracking, Recursion, DFS, really fast,
    private static String[] letterMap = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations1(String digits) {
        List<String> letterCombinations = new LinkedList<>();

        if (digits != null && digits.length() > 0) {
            combine(new StringBuilder(), digits, 0, letterCombinations);
        }

        return letterCombinations;
    }

    private static void combine(StringBuilder combination, String digits, int position, List<String> letterCombinations) {
        if (position >= digits.length()) {
            letterCombinations.add(combination.toString());
            return;
        }

        String letters = letterMap[digits.charAt(position) - '2'];
        for (char character : letters.toCharArray()) {
            // cannot use position++ or ++position
            combine(combination.append(character), digits, position + 1, letterCombinations);

            combination.deleteCharAt(combination.length() - 1); // backtrack
        }
    }

}