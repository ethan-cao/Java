package algorithm.colllection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Binary search tree implementation for symbol table
 * binary tree, each node has a key and
 * the key is larger than all keys in its left subtree
 * the key is smaller than all keys in its right subtree
 */

public class BST<Key extends Comparable, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size = 1; // number of nodes in this subtree

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BST<Integer, String> symbolTable = new BST<>();

        symbolTable.put(3, "C");
        symbolTable.put(1, "A");
        symbolTable.put(2, "B");
        symbolTable.put(4, "B");
    }

    public int size() {
        return this.size(this.root);
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    public boolean isEmpty() {
        return this.root.size == 0;
    }

    public void put(Key key, Value value) {
        Node node = new Node(key, value);

        if (this.root == null) {
            this.root = node;
            return;
        }

        Node current = this.root;

        while (true) {
            int cmp = current.key.compareTo(key);

            if (cmp == 0) {
                current.value = value;
                break;
            } else if (cmp > 0) {
                current.size++;
                if (current.left == null) {
                    current.left = node;
                    break;
                } else {
                    current = current.left;
                }
            } else {
                current.size++;
                if (current.right == null) {
                    current.right = node;
                    break;
                } else {
                    current = current.right;
                }
            }
        }
    }

    public Value get(Key key) {
        Node current = this.root;
        Value value = null;

        while (current != null) {
            int cmp = current.key.compareTo(key);

            if (cmp == 0) {
                value = current.value;
                break;
            } else if (cmp > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return value;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     */
    public void deleteMin() {
        if (this.isEmpty()) {
            return;
        }

        this.deleteMin(this.root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    // Returns the smallest key in the symbol table.
    public Key minKey() {
        if (isEmpty()) {
            return  null;
        }

        return getMin(root).key;
    }

    private Node getMin(Node x) {
        if (x.left == null) return x;
        else return getMin(x.left);
    }

    public void delete(Key key) {
        if (this.isEmpty()) {
            return;
        }

        this.delete(this.root, key);
    }

    private Node delete(Node node, Key key) {
        int cmp = node.key.compareTo(key);

        if ( cmp > 0) {
            this.delete(node.left, key);
        } else if (cmp < 0 ){
            this.delete(node.right, key);
        } else {
            if (node.right == null){
                return node.left;
            }

            if (node.left == null){
                return node.right;
            }

            Node current = node;
            node = this.getMin(current.right);
            node.left = current.left;
            node.right = this.deleteMin(current.right);
        }

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Iterable<Key> keys() {
        Deque<Key> keys = new ArrayDeque<>();
        this.inorder(this.root, keys);
        return keys;
    }

    private void inorder(Node node, Deque<Key> keys) {
        if (node == null) {
            return;
        }

        this.inorder(node.left, keys);
        keys.push(node.key);
        this.inorder(node.right, keys);
    }

    private void preorder(Node node, Deque<Key> keys) {
        if (node == null) {
            return;
        }

        keys.push(node.key);
        this.inorder(node.left, keys);
        this.inorder(node.right, keys);
    }

    private void postorder(Node node, Deque<Key> keys) {
        if (node == null) {
            return;
        }

        this.inorder(node.left, keys);
        this.inorder(node.right, keys);
        keys.push(node.key);
    }

    private void BFS(Node node, Deque<Key> keys) {

    }
}
