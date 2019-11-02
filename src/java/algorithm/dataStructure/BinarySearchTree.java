package algorithm.dataStructure;

import algorithm.sorting.SelectionSort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Binary search tree implementation for symbol table (Map)
 *
 * binary tree, each node has a key and
 * the key is larger than all keys in its left subtree
 * the key is smaller than all keys in its right subtree
 */

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        bst.put(3, "C");
        bst.put(1, "A");
        bst.put(2, "B");
        bst.put(4, "B");

        // in-order visit : 1,2,3,4
        for (Integer i : bst.keys()) {
            System.out.println(i);
        }
    }

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

    private Node root;

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
        Value value = null;

        Node current = this.root;
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

    // Return the number of keys in the symbol table strictly less than {@code key}.
    public int rank(Key key) {
        return key == null ? 0 : this.rank(key, this.root);
    }

    private int rank(Key key, Node node) {
        int cmp = key.compareTo(node.key);

        if (cmp > 0) {
            return 1 + this.size(node.left) + this.rank(key, node.right);
        } else if (cmp < 0) {
            return this.rank(key, node.left);
        } else {
            return this.size(node.left);
        }
    }

    public Deque<Key> keys() {
        Deque<Key> keys = new ArrayDeque<>();

        this.inOrder(this.root, keys);

        return keys;
    }

    /**
     * Tree traversal algorithms
     * DFS : pre-order, in-order, post-order
     * BFS
     *
     * All four traversals require O(n) time as they visit every node exactly once.
     */

    // traversal result is in queue FIFO
    private void inOrder(Node node, Deque<Key> queue) {
        if (node == null) {
            return;
        }

        this.inOrder(node.left, queue);
        queue.offer(node.key);
        this.inOrder(node.right, queue);
    }

    // Iterative, Stack, reuse call stack
    // Time O(n), Space: O(n)
    public void inOrder1(Node root, Deque<Key> queue) {
        Deque<Node> stack = new ArrayDeque<>();

        Node currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pop();
            queue.offer(currentNode.key);
            currentNode = currentNode.right;
        }
    }

    private void preOrder(Node node, Deque<Key> queue) {
        if (node == null) {
            return;
        }

        queue.offer(node.key);
        this.inOrder(node.left, queue);
        this.inOrder(node.right, queue);
    }

    private void postOrder(Node node, Deque<Key> queue) {
        if (node == null) {
            return;
        }

        this.inOrder(node.left, queue);
        this.inOrder(node.right, queue);
        queue.offer(node.key);
    }

    // traversal result is in visited queue, FIFO
    private void BFS(Node root, Deque<Node> queue) {
        if (root == null) {
            return;
        }

        Deque<Node> visited = new ArrayDeque<>();
        visited.offer(root);

        while (!queue.isEmpty()) {
            Node node = visited.poll();
            queue.offer(node);

            if (node.left != null) {
                visited.offer(node.left);
            }

            if (node.right != null) {
                visited.offer(node.right);
            }
        }

    }

}