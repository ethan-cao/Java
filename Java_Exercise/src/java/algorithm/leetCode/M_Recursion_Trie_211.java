package algorithm.leetCode;

import algorithm.dataStructure.Trie.TrieNode;

/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word) : it can search a literal word or
a regular expression string containing only letters a-z or .    A . means it can represent any one letter.

### Example

### Condition

### Essential problem

### Corner case

*/

public class M_Recursion_Trie_211 {
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

    static class WordDictionary {
        TrieNode root = new TrieNode();

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode current = this.root;

            for (char character : word.toCharArray()) {
                if (!current.containsKey(character)) {
                    current.put(character, new TrieNode());
                }

                current = current.get(character);
            }

            current.setAsWord();
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
        private boolean match(char[] chars, int k, TrieNode node) {
            if (k == chars.length) {
                return node.isWord();
            }

            if (chars[k] == '.') {
                // if it is .  just pick the 1st not null value
                for (int i = 0; i < TrieNode.SIZE; ++i) {
                    char idx = (char) (i + 'a');
                    if (node.containsKey(idx) && this.match(chars, k + 1, node.get(idx))) {
                        return true;
                    }
                }
            } else {
                return node.containsKey(chars[k]) && this.match(chars, k + 1, node.get(chars[k]));
            }

            return false;
        }
    }




}

