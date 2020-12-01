package algorithm.leetCode;
/*
Implement a trie with insert, contains, and startsWith methods.

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.

### Example
Trie trie = new Trie();
trie.insert("apple");
trie.contains("apple");   // returns true
trie.contains("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.contains("app");     // returns true

*/

import algorithm.dataStructure.Trie;

public class M_Trie_208 {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.contains("apple"));   // true
        System.out.println(trie.contains("app"));     // false
        System.out.println(trie.startsWith("app"));       // true
        System.out.println(trie.startsWith("asp"));       // false

        trie.insert("app");
        System.out.println(trie.contains("app"));     // true
    }
}

