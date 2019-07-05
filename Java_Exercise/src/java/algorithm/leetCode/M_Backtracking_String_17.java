package algorithm.leetCode;


/*
Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Please solve it without division and in O(n).

Could you solve it with constant space complexity?
(The output array does not count as extra space for the purpose of space complexity analysis.)

Although the above answer is in lexicographical order, your answer could be in any order you want.

### Example
"23" -> ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

*/

import java.util.ArrayList;
import java.util.List;

public class M_Backtracking_String_17 {

    public static void main(String... args) {
        System.out.println(letterCombinations(""));  // []
        System.out.println(letterCombinations("1"));  // []
        System.out.println(letterCombinations("23")); //  ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    }

    public static List<String> letterCombinations(String digits) {
        List<String> letterCombinations = new ArrayList<>();

        return letterCombinations;
    }
}
