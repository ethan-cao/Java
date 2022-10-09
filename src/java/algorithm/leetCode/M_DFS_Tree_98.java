package algorithm.leetCode;

/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

### Example
    2
   / \
  1   3
[2,1,3] -> true

    5
   / \
  1   4
     / \
    3   6
[5,1,4,null,null,3,6] ->  false
The root node's value is 5 but its right child's value is 4.
*/

import java.util.*;

public class M_DFS_Tree_98 {

    public static void main(String... args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n11 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n1.left = n11;

        System.out.println(isValidBST2(n1));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Time O(logN), Space: O().
    // Recursion 0ms
    public static boolean isValidBST(TreeNode root) {
        return verifyBST(root, null, null);
    }

    private static boolean verifyBST(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if (min != null && node.val <= min) {
            return false;
        }

        if (max != null && node.val >= max) {
            return false;
        }

        return verifyBST(node.left, min, node.val) && verifyBST(node.right, node.val, max);
    }

    // inorder traversal, iterative
    public static boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode currentNode = root;
        TreeNode previousNode = null;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pop();

            // !!! BST inorder traversal should iterate value in ascending order
            if (previousNode != null && currentNode.val <= previousNode.val) {
                return false;
            }
            previousNode = currentNode;
            currentNode = currentNode.right;
        }

        return true;
    }

    // inorder traversal, recursive, 0ms
    private static TreeNode previousNode = null;

    public static boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean isValidLeftBST = isValidBST2(root.left);

        boolean isValidNode = (previousNode != null && root.val <= previousNode.val) ? false : true;
        previousNode = root;

        boolean isValidRightBST = isValidBST2(root.right);

        return isValidLeftBST && isValidNode && isValidRightBST;
    }

    // an iterative version that works for BST with duplicate values.
    // (Below, duplicate values must be on the left side of a node.
    // If it needs to be on right side, then just switch the > and >= signs.)
    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        boolean onRightSideOfPrev = false;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && ((!onRightSideOfPrev && prev.val > root.val) || (onRightSideOfPrev && prev.val >= root.val))) {
                return false;
            }
            prev = root;
            root = root.right;
            onRightSideOfPrev = root == null ? false : true;
        }
        return true;
    }

}