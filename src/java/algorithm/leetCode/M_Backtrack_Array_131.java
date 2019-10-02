package algorithm.leetCode;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

### Example
Input: "aab"
Output: [ ["aa","b"],  ["a","a","b"] ]

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Backtrack_Array_131 {

    public static void main(String... args) {
        int[] candidates = {2, 3, 6, 7};

        List<List<String>> results = partition("aab");
        for (List<String> result : results) {
            System.out.println(Arrays.toString(result.toArray()));
        }
        //  [ ["aa","b"],  ["a","a","b"] ]
    }

    // Backtrack
    private static List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();


        return partitions;
    }

}