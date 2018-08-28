package algorithm.colllection;

/**
 * Left Lean Red Black (LLRB) tree, Represent 2-3 tree as a Binary search tree
 * 2-3 tree: tree contains 2-3 node
 * 2-3 nodes :  B-D
 * / | \
 * A  C  E
 *
 * <p>
 * No node has two red links connected to it
 * Every path from root to null link has the same number of black links (perfect black balance, 2-3 tree are perfect balanced)
 * Red links lean left
 */
public class RedBlackBST<Key extends Comparable, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color = BLACK;

        private int size = 1; // number of nodes in this subtree

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        private boolean isRed(Node node) {
            return node != null && (node.color == RED);
        }
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

    private boolean isRed(Node node) {
        return node.color == RED;
    }

    // identical with BST, but faster than BST, because it is balanced
    public Value get(Key key) {
        Node current = this.root;
        Value value = null;

        while (current != null) {
            int cmp = current.key.compareTo(key);

            if (cmp > 0) {
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

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, RED);
        }

        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            node.left = this.put(node.left, key, value);
        } else if (cmp < 0) {
            node.right = this.put(node.right, key, value);
        } else {
            node.value = value;
        }

        // @see 10_Balanced_Search_Trees.pdf P32 for the following cases

        // if lean right, rotate to lean left
        if (this.isRed(node.right) && !this.isRed(node.left)) {
            node = rotateLeft(node);
        }

        // if it is 3-4 node, rotate the right most one
        if (this.isRed(node.left) && this.isRed(node.left.left)) {
            node = rotateRight(node);
        }

        // if both left and right node are red, flip the color
        if (this.isRed(node.left) && this.isRed(node.right)) {
            this.flipColors(node);
        }

        return node;
    }

    public void put(Key key, Value value) {
        this.put(this.root, key, value);
    }

    // orient a right leaning red link to lean left
    // node.right.color is RED, rotateLeft() makes node.color RED
    private Node rotateLeft(Node node) {
        Node right = node.right;

        node.right = right.left;
        right.left = node;

        right.color = node.color;
        node.color = RED;

        return right;
    }

    // node.left.color is RED, rotateRight() makes node.color RED
    private Node rotateRight(Node node) {
        Node left = node.left;

        left.right = node;
        node.left = left.right;

        left.color = node.color;
        node.color = RED;

        return left;
    }

    // node.left.color is RED, node.right.color is RED, node.color is BLACK
    // flipColors() flip colors for node, node.left, node.right
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
}