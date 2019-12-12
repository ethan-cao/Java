package algorithm.leetCode;

/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

### Example
Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").

Input:s1= "ab" s2 = "eidboaoo"
Output: False

### Review:

*/

import java.util.*;

public class M_2Pointer_SlidingWindow_String_567 {

    public static void main(String... args) {
        System.out.println(checkInclusion("ab", "eidbaooo")); // T
        System.out.println(checkInclusion("ab", "eidboaoo")); // F
    }

    public static boolean checkInclusion(String s1, String s2) {
        return false;
    }

}