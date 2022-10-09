package algorithm.leetCode;

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.
word may contain dots '.' where dots can be matched with any letter.

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.

Implement the WordDictionary class:
    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.

*/

import java.util.*;

public class M_Design_Recursion_Trie_211 {

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();

        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        System.out.println(wd.search("pad")); // false
        System.out.println(wd.search("bad")); // true
        System.out.println(wd.search(".ad")); // true
        System.out.println(wd.search("b.")); // false
        System.out.println(wd.search("b..")); //  true
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    // Trie 50ms
    static class WordDictionary {
        TrieNode root = new TrieNode();

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode();
                }

                current = current.children[c - 'a'];
            }

            current.isWord = true;
        }

        /**
         * Returns if the word is in the data structure.
         * A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return this.match(word.toCharArray(), 0, this.root);
        }

        /**
         * @param chars target to search for
         * @param k     index, which char is inspecting now
         * @param node  node to start searching from
         */
        private boolean match(char[] chars, int idx, TrieNode node) {
            if (idx >= chars.length) {
                return node.isWord;
            }

            if (chars[idx] == '.') {
                for (TrieNode child : node.children) {
                    // try all possible combination to find a match
                    // as long as there is a case that match all, return true
                    // this is why it must be recursion !!!
                    if (child != null && this.match(chars, idx + 1, child)) {
                        return true;
                    }
                }
            } else {
                TrieNode child = node.children[chars[idx] - 'a'];
                return child != null && this.match(chars, idx + 1, child);
            }

            return false;
        }
    }

    class WordDictionary1 {
        Map<Integer, List<String>> map = new HashMap<>();

        public WordDictionary1() {
            map = new HashMap<>();
        }

        public void addWord(String word) {
            if (!map.containsKey(word.length())) {
                map.put(word.length(), new ArrayList<>());
            }
            map.get(word.length()).add(word);
        }

        public boolean search(String word) {
            int len = word.length();
            List<String> list = map.get(len);

            if (list != null) {
                for (String w : list) {
                    if (isSame(w, word)) return true;
                }
            }

            return false;
        }

        private boolean isSame(String w, String word) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != '.' && word.charAt(i) != w.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }


}

