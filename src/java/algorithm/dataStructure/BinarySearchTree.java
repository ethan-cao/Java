package algorithm.dataStructure;

import algorithm.leetCode.M_Stack_Tree_144;

import java.util.*;

/**
 * Binary search tree implementation for symbol table (Map)
 *
 * binary tree, each node has a key and
 * the key is larger than all keys in its left subtree
 * the key is smaller than all keys in its right subtree
 *
 *
 * use TreeMap as Java native Rea Black Tree, which is also BST
 */

class BST_Test {

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

//        List<BinarySearchTree.Node> bsfTraversal = bst.BFS1(bst.root);
    }

}

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    class Node {
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

    public Node root;

    public int size() {
        return this.size(this.root);
    }

    // if size is not know
    public int count(Node node) {
        if (node == null) {
            return 0;
        }

        return count(node.left) + 1 + count(node.right);
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
     *
     * @param node root of the subtree
     * @return the removed node
     * @throws NullPointerException if the subtree root is empty
     * @since 1.0
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
     * DFS: pre-order, in-order, post-order
     * BFS
     *
     * All four traversals require O(n) time as each node is visited exactly once.
     */

    // traversal result is in queue FIFO
    // BST inorder traversal should iterate value in ascending order
    private void inOrder(Node node, Deque<Key> queue) {
        if (node == null) {
            return;
        }

        this.inOrder(node.left, queue);
        queue.offer(node.key);  // this is the actual visiting part
        this.inOrder(node.right, queue);
    }

    // LC 94
    public void inOrder1(Node root, Deque<Key> queue) {
        Deque<Node> stack = new ArrayDeque<>();

        Node currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {

            // push all left nodes to stack
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

    // LC 144
    public List<Value> preorder1(Node root) {
        List<Value> traversal = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();

        Node current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                // current is the left child in previous iteration
                // current is also the parent in this iteration
                traversal.add(current.value);
                stack.push(current);

                current = current.left;
            }

            // current.left is null now, finished visiting parent and its left child

            current = stack.pop();
            current = current.right;
        }

        return traversal;
    }

    private void postOrder(Node node, Deque<Key> queue) {
        if (node == null) {
            return;
        }

        this.inOrder(node.left, queue);
        this.inOrder(node.right, queue);
        queue.offer(node.key);
    }

    // LC 145
    public List<Value> postOrder1(Node root) {
        List<Value> traversal = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();

        Node current = root;

        while (current != null || !stack.isEmpty()) {

            // visit until the leaf
            while (current != null) {
                stack.push(current);

                // visit left right, then right, as in post-order
                if (current.left != null) {
                    current = current.left;
                } else {
                    current = current.right;
                    // check in the end is to sure right is always visited
                }
            }

            // node as parent, its left and right child are both null
            Node node = stack.pop();
            traversal.add(node.value);

            // visit node's right sibling if it exists
            //         stack.peek()
            //         /         \
            //       node        to be visited
            if (!stack.isEmpty() && stack.peek().left == node) {
                current = stack.peek().right;
            }
        }

        return traversal;
    }

    // traversal result is in visited traversal, FIFO
    private List<Node> BFS(Node root) {
        List<Node> traversal = new ArrayList<>();

        if (root == null) {
            return traversal;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            traversal.add(node);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return traversal;
    }

    // using DFS pre-order to implement BFS
    public List<List<Node>> BFS1(Node root) {
        List<List<Node>> traversal = new ArrayList<>();

        preorderVisit(root, 0, traversal);

        return traversal;
    }

    private void preorderVisit(Node node, int depth, List<List<Node>> traversal) {
        if (node == null) {
            return;
        }

        if (depth == traversal.size()) {
            traversal.add(new ArrayList<>());
        }

        traversal.get(depth).add(node);
        preorderVisit(node.left, depth + 1, traversal);
        preorderVisit(node.right, depth + 1, traversal);
    }

    // verify BST: algorithm.leetCode.M_Stack_Tree_98.isValidBST
}