package algorithm.colllection;

/**
 * Represent 2-3 tree as a Binary search tree
 *
 *
 *      No node has two red links connected to it
 *      Every path from root to null link has the same number of black links (perfect black balance, 2-3 tree are perfect balanced)
 *      Red links lean left
 *
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
        private boolean color;

        private int size = 1; // number of nodes in this subtree

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        private boolean isRed(Node node){
            return node != null && (node.color == RED);
        }
    }
}
