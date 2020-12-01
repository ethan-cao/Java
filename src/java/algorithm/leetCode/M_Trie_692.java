package algorithm.leetCode;

/*
Given a non-empty list of words, return the k most frequent elements.
Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical order comes first.
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

### Example
["i", "love", "leetcode", "i", "love", "coding"], k = 2
-> ["i", "love"]
    "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.


["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
-> ["the", "is", "sunny", "day"]
    "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

*/

import java.util.*;

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

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    // Heap, 5ms - 6ms
    // Time: O(N log K), Space: O(N)
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> Objects.equals(a.getValue(), b.getValue()) ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()
        );

        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
             maxHeap.offer(entry);
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            result.add( maxHeap.poll().getKey());
        }

        return result;
    }

    // Trie & HashMap, 5ms - 12ms
    public static List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> counter = new HashMap<>();
        int maxFrequency = Integer.MIN_VALUE;
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
            maxFrequency = Math.max(maxFrequency, counter.get(word));
        }

        // Trie here is essentially similar to radix sort.
        TrieNode[] bucket = new TrieNode[maxFrequency + 1];

        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            if (bucket[frequency] == null) {
                bucket[frequency] = new TrieNode();
            }

            TrieNode node = bucket[frequency];

            buildTrie(node, word);
        }

        // collect result
        List<String> result = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; --i) {
            getWord(bucket[i], result, k);
            // since bucket[i] is trie, search string in it outperform other structure
        }

        return result;
    }

    private static void buildTrie(TrieNode root, String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null) {
                current.children[c - 'a'] = new TrieNode();
            }

            current = current.children[c - 'a'];
        }

        current.word = word;
    }

    private static void getWord(TrieNode node, List<String> result, int k) {
        if (node == null || result.size() >= k) {
            return;
        }

        for (TrieNode child : node.children) {
            if (result.size() >= k) {
                return;
            }

            if (child == null) {
                continue;
            }

            if (child.word != null) {
                result.add(child.word);
            }

            getWord(child, result, k);
        }
    }

}


