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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class M_Tree_98 {

    public static void main(String... args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n2.left = n1;
        n2.right = n3;

        System.out.println(isValidBST(n1));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Time O(), Space: O()
    public static boolean isValidBST(TreeNode root) {
        return false;
    }
}