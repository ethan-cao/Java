package algorithm.leetCode;

/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

### Example
s = "eceba", k = 2  -> 3
s = "aa", k = 1 -> 2

*/

public class H_2Pointer_SlidingWindow_String_340 {

    public static void main(String... args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // 3
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1)); // 2
    }

    // Two pointer - Sliding window, 2ms
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int longestLength = 0;

        if (s == null || s.length() == 0) {
            return longestLength;
        }

        int left = 0;
        int right = 0;

        int[] frequencies = new int[128];
        int distinctCharCount = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            frequencies[rightChar]++;

            if (frequencies[rightChar] == 1) {
                distinctCharCount++;
            }

            while (distinctCharCount > k && left <= right) {
                char leftChar = s.charAt(left);
                frequencies[leftChar]--;

                if (frequencies[leftChar] == 0) {
                    distinctCharCount--;
                }

                left++;
            }

            longestLength = Math.max(longestLength, right - left + 1);

            right++;
        }

        return longestLength;
    }

}
