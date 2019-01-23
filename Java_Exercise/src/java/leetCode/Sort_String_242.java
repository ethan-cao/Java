package leetCode;

/*
Given two strings s and t , write a function to determine if t is an anagram of s.

anagram :  use exact the same char once

extra: What if the inputs contain unicode characters? How would you adapt your solution to such case?

### Input
String s, String t

### Output
boolean

### Example
Input: s = "anagram", t = "nagaram"
Output: true

Input: s = "rat", t = "car"
Output: false

### Condition
You may assume the string contains only lowercase alphabets.

### Essential problem

### Corner case

*/


import java.util.Arrays;

public class Sort_String_242 {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        s = "lives";
        t = "Elvis";

        boolean result = isAnagram(s, t);

        System.out.println(result);
    }

    public static boolean isAnagram(String s, String t) {
        if (null == s || null == t || s.length() != t.length()) {
            return false;
        }

        boolean result = true;

        char[] tempS = s.toCharArray();
        char[] tempT = t.toCharArray();

        Arrays.sort(tempS);
        Arrays.sort(tempT);

        for (int i = 0; i < s.length(); ++i) {
            if (tempS[i] != tempT[i]) {
                result = false;
                break;
            }
        }

        return result;
    }

    public static boolean isAnagram1(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }
}
