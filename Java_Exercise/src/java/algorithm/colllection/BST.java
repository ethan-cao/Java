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
        private Node left;
        private Node right;
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
        return this.size() == 0;
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

            if (cmp  > 0) {
                current = current.left;
            } else if (cmp < 0) {
                current = current.right;
            } else {
                value = current.value;
                break;
            }
        }

        return value;
    }

    /**
     * Returns the smallest key in the symbol table.
     */
    public Key getMinKey() {
        if (isEmpty()) {
            return null;
        }

        return getMinNode(root).key;
    }

    /**
     * Returns the smallest key in the subtree
     */
    private Node getMinNode(Node x) {
        if (x.left == null) {
            return x;
        }

        return getMinNode(x.left);
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     */
    public void deleteMinNode() {
        if (this.isEmpty()) {
            return;
        }

        this.deleteMinNode(this.root);
    }

    /**
     * Removes the smallest Node from the subtree
     */
    private Node deleteMinNode(Node node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = deleteMinNode(node.left);
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    public void delete(Key key) {
        if (this.isEmpty()) {
            return;
        }

        this.delete(this.root, key);
    }

    // return the updated subtree with the node removed
    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            node.left = this.delete(node.left, key);
        } else if (cmp < 0) {
            node.right = this.delete(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }

            if (node.left == null) {
                return node.right;
            }

            // use the smallest node in the right subtree to replace the removed node
            Node current = node;  // need to reuse node variable outside else, so declare another one
            node = this.getMinNode(current.right);
            node.left = current.left;
            node.right = this.deleteMinNode(current.right);
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
