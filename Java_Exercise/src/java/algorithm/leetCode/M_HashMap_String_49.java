package algorithm.leetCode;

import java.util.*;

/*
Given an array of strings, group anagrams together.

All inputs will be in lowercase.
The order of your output does not matter.

### Example
 ["eat", "tea", "tan", "ate", "nat", "bat"],
->
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

*/
public class M_HashMap_String_49 {

    public static void main(String... args) {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strings);
        for (List<String> list : result) {
            for (String string : list) {
                System.out.print(string + " ");
            }
            System.out.print(System.lineSeparator());
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String string : strs) {
            String key = getAnagramKey(string);

            List<String> anagrams = map.getOrDefault(key, new ArrayList<>());
            anagrams.add(string);
            map.put(key, anagrams);
        }

        return new ArrayList<>(map.values());
    }

    private static String getAnagramKey(String string){
        char[] chars = string.toCharArray();

        Arrays.sort(chars);

        return String.valueOf(chars);
    }

}
