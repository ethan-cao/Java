package algorithm.leetCode;

/*
Given a string, find the first non-repeating character in it and return it's index.
If it doesn't exist, return -1.

You may assume the string contain only lowercase letters.

### Example
"leetcode" -->  0
"loveleetcode" --> 2

### Condition

### Essential problem

### Corner case

*/

import java.util.LinkedHashMap;
import java.util.Map;

public class E_String_387 {

    public static void main(String... args) {
        String s = "leetcode"; // 0
        s = "loveleetcode"; // 2
        s = ""; // -1
        s = "fferwerwer"; // -1

        System.out.println(firstUniqChar1(s));
    }

    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        char firstNonRepeatingChar = 0;
        for (Map.Entry entry : map.entrySet()) {
            int count = (int) entry.getValue();

            if (1 == count) {
                firstNonRepeatingChar = (char) entry.getKey();
                break;
            }
        }

        return firstNonRepeatingChar == 0 ? -1 : s.indexOf(firstNonRepeatingChar);
    }

    public static int firstUniqChar1(String s) {
        int[] alphabet = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            alphabet[s.charAt(i) - 'a']++;
        }

        // this guarantees to find the 1st non-repeating char
        for (int i = 0; i < s.length(); ++i) {
            if (alphabet[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }
}
