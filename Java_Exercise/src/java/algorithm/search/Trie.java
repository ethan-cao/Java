package algorithm.search;


// https://leetcode.com/articles/implement-trie-prefix-tree/?orderBy=most_votes
public class Trie {
    private TrieNode root; // root is an empty node

    public Trie() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot(){
        return this.root;
    }

    // Time complexity : O(n), where n is the key length.
    public void insert(String word) {
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
     * Returns if the word is in the trie.
     */
    // Time complexity : O(n), where n is the key length.
    // 211 enhanced contains
    public boolean contains(String word) {
        TrieNode current = this.root;

        for (char character : word.toCharArray()) {
            if (!current.containsKey(character)) {
                return false;
            }

            current = current.get(character);
        }

        return current.isWord();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    // Time complexity : O(n), where n is the key length.
    public boolean startsWith(String prefix) {
        TrieNode current = this.root;

        for (char character : prefix.toCharArray()) {
            if (!current.containsKey(character)) {
                return false;
            }

            current = current.get(character);
        }

        return true;
    }

    public static class TrieNode {
        private boolean isWord;  // True : from root until this node, it is a word

        public static final int SIZE = 26;
        private TrieNode[] children = new TrieNode[SIZE]; // each slot store each character a...z
        // the position this node occupies implies what value it has

        public boolean containsKey(char ch) {
            return this.children[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return this.children[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            this.children[ch - 'a'] = node;
        }

        public void setAsWord() {
            this.isWord = true;
        }

        public boolean isWord() {
            return this.isWord;
        }
    }
}
