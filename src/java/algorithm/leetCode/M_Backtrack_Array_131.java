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
        List<List<String>> results = partition("aab");
        for (List<String> result : results) {
            System.out.println(Arrays.toString(result.toArray()));
        }
        //  [ ["aa","b"],  ["a","a","b"] ]
    }

    private static List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();
        List<String> tracker = new ArrayList<>();

        collectPartitions(s, partitions, tracker, 0);

        return partitions;
    }

    private static void collectPartitions(String s, List<List<String>> partitions, List<String> tracker, int start) {
        if (start == s.length()) {
            partitions.add(new ArrayList<>(tracker));
            return;
        }

        for (int i = start; i < s.length(); ++i) {
            String substring = s.substring(start, i + 1);
            if (!isPalindrome(substring)) {
                continue;
            }

            tracker.add(substring);
            collectPartitions(s, partitions, tracker, i + 1);
            tracker.remove(tracker.size() - 1);
        }
    }

    // !!! memorise !!! check if string is palindrome
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

}