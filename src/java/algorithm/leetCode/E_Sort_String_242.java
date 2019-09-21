package algorithm.leetCode;

/*
Given two strings s and t , write a function to determine if t is an anagram of s.

anagram :  use exact the same char once

extra: What if the inputs contain unicode characters? How would you adapt your solution to such case?

### Example
Input: s = "anagram", t = "nagaram"
Output: true

Input: s = "rat", t = "car"
Output: false

### Condition
You may assume the string contains only lowercase alphabets.

### Essential problem

### Corner case

R :
*/


import java.util.Arrays;

public class E_Sort_String_242 {
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


        char[] tempS = s.toCharArray();
        char[] tempT = t.toCharArray();

        Arrays.sort(tempS);
        Arrays.sort(tempT);

        return String.valueOf(tempS).equals(String.valueOf(tempT));

//        boolean result = true;
//        for (int i = 0; i < s.length(); ++i) {
//            if (tempS[i] != tempT[i]) {
//                result = false;
//                break;
//            }
//        }
//        return result;
    }

    public static boolean isAnagram1(String s, String t) {
        if (null == s || null == t || s.length() != t.length()) {
            return false;
        }

        int[] alphabet = new int[26];

        for (char c: s.toCharArray()  ){
            alphabet[c-'a']++;
        }

        for (char c: t.toCharArray()  ){
            alphabet[c-'a']--;
        }

        for (int i :alphabet){
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}
