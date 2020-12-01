package algorithm.leetCode;

/*
Implement an iterator over a binary search tree (BST).
Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid,
that is, there will be at least a next smallest number in the BST when next() is called.

### Example
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false

*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class M_Tree_173 {
}


class BSTIterator {
    private TreeNode current;
    private Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        this.current = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !this.stack.isEmpty() || this.current != null;
    }

    /** @return the next smallest number */
    // o binary search tree Inorder traversal.
    public int next() {
        while ( this.current != null) {
            this.stack.push(this.current);
            this.current = this.current.left;
        }

        this.current = this.stack.pop();  // inorder, stack.peekFirst() has empty left child
        int smallest = this.current.val;

        this.current = this.current.right;

        return smallest;
    }
}