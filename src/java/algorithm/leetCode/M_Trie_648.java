package algorithm.leetCode;

/*
In English, we have a concept called root, which can be followed by some other words to form another longer word
let's call this word successor. e.g., the root an, followed by other, which can form another word another.
Now, given a dictionary consisting of many roots and a sentence.
You need to replace all the successor in the sentence with the root forming it.
If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

### Example
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

*/

import java.util.*;

public class M_Trie_648 {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    // Trie, 6ms
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        TrieNode root = buildTrie(dictionary);

        for (String word : words) {
            sb.append(findReplacement(root, word)).append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }

    private TrieNode buildTrie(List<String> dictionary) {
        TrieNode root = new TrieNode();

        for (String word : dictionary) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode();
                }
                current = current.children[c - 'a'];
            }

            current.word = word;
        }

        return root;
    }

    private String findReplacement(TrieNode node, String s) {
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i) - 'a';

            if (node.children[c] == null) {
                return s;
            }

            node = node.children[c];

            if (node.word != null) {
                return node.word;
            }
        }

        return s;
    }


    // Time: O(N^2)  24ms
    public String replaceWords1(List<String> dictionary, String sentence) {
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");

//      Collections.sort(dictionary, (a, b) -> a.length() - b.length() );
        Collections.sort(dictionary, Comparator.comparingInt(String::length));

        for (String word : words) {
            boolean isReplaced = false;

            for (String root : dictionary) {
                if (word.startsWith(root)) {
                    sb.append(root);
                    isReplaced = true;
                    break;
                }
            }

            if (!isReplaced) {
                sb.append(word);
            }

            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }

}

