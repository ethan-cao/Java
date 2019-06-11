package leetCode;

/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical order comes first.

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

### Example
["i", "love", "leetcode", "i", "love", "coding"], k = 2
->
["i", "love"]
Explanation:
    "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.


["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
->
["the", "is", "sunny", "day"]
Explanation:
    "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

### Condition

### Essential problem

### Corner case

*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class M_Trie_692 {

    public static void main(String[] args) {
        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
//        System.out.println(Arrays.toString(topKFrequent(words1, 2).toArray())); // ["i", "love"]
//        System.out.println(Arrays.toString(topKFrequent(words1, 1).toArray())); // ["i"]

        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
//        System.out.println(Arrays.toString(topKFrequent(words2, 4).toArray())); // ["the", "is", "sunny", "day"]
//        System.out.println(Arrays.toString(topKFrequent(words2, 1).toArray())); // ["the"]

        String[] words3 = {"a", "aa", "aaa"};
        System.out.println(Arrays.toString(topKFrequent(words3, 2).toArray())); // ["a", "aa"]
    }

    public static List<String> topKFrequent(String[] words, int k) {
        // Count frequency
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        // Trie here is essentially similar to radix sort.
        TrieNode[] tally = new TrieNode[words.length + 1]; // the max possible position is word.length
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {

            TrieNode root = tally[entry.getValue()];
            if (root == null) {
                root = new TrieNode();
                tally[entry.getValue()] = root;
            }

            insertWord(root, entry.getKey());
        }

        // collect result
        List<String> result = new LinkedList<>();
        for (int i = tally.length - 1; i >= 0; --i) {
            getWord(tally[i], result, k);
        }

        return result;
    }

    private static void insertWord(TrieNode root, String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null) {
                current.children[c - 'a'] = new TrieNode();
            }

            current = current.children[c - 'a'];
        }

        current.word = word;
    }

    private static void getWord(TrieNode root, List<String> result, int k) {
        if (root == null || result.size() >= k) {
            return;
        }

        for (int i = 0; i < 26; ++i) {
            TrieNode child = root.children[i];

            if (child == null) {
                continue;
            }

            if (child.word != null) {
                result.add(child.word);
            }

            getWord(child, result, k);
        }
    }

    private static class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private String word;
    }
}


