package leetCode;
/*
Implement a trie with insert, search, and startsWith methods.

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.

### Example
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true

### Condition

### Essential problem

### Corner case

*/

public class M_Trie_208 {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("app"));     // false
        System.out.println(trie.startsWith("app"));       // true
        System.out.println(trie.startsWith("asp"));       // false

        trie.insert("app");
        System.out.println(trie.search("app"));     // true
    }
}

// https://leetcode.com/articles/implement-trie-prefix-tree/?orderBy=most_votes
class Trie {
    private TrieNode root; // root is an empty node

    public Trie() {
        this.root = new TrieNode();
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
    // 211 enhanced search
    public boolean search(String word) {
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
}

class TrieNode {
    private boolean isWord;  // True : from root until this node, it is a word

    public static final int SIZE = 26;
    private TrieNode[] children = new TrieNode[SIZE]; // each slot store each character a...z

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
