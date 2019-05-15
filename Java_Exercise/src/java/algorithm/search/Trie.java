package algorithm.search;

import java.util.Map;

public class Trie {
    private TrieNode root;
}

class TrieNode{
    private String value;
    private Map<Character, TrieNode> children;
    private boolean isWord;

    TrieNode(String value){
        this.value = value;
    }

}
