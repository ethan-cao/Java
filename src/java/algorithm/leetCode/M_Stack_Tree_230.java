package algorithm.leetCode;

/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

What if the BST is modified (insert/delete operations) often
and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

### Example
Input: root = [3,1,4,null,2], k = 1   ==> 1
   3
  / \
 1   4
  \
   2

root = [5,3,6,2,4,null,null,1], k = 3  ==>  3
       5
      / \
     3   6
    / \
   2   4
  /
 1
*/

import java.util.*;

public class M_Stack_Tree_230 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // inorder, iterative
    // Time O(n), Space: O(n)
    public static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int count = 0;

        TreeNode currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {

            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pop();

            count++;
            if (count == k) {
                return currentNode.val;
            }

            currentNode = currentNode.right;
        }

        return -1;
    }

    // inorder, recursive
    // Time O(n), Space: O(n)
    private static int count = 0;
    private static int kthSmallest = -1;

    public static int kthSmallest1(TreeNode root, int k) {
        inorderVisit(root, k);
        return kthSmallest;
    }

    private static void inorderVisit(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        inorderVisit(node.left, k);

        count++;
        if (count == k) {
            kthSmallest = node.val;
            return;
        }

        inorderVisit(node.right, k);
    }

    // Binary search
    // best: O(N), worst: O(N^2)
    public static int kthSmallest2(TreeNode root, int k) {
        int leftChildCount = countNodes(root.left);

        if (leftChildCount == k - 1) {
            return root.val;
        }

        if (leftChildCount >= k) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - 1 - leftChildCount);
        }
    }

    // count number of nodes in this subtree rooted at node
    private static int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return countNodes(node.left) + 1 + countNodes(node.right);
    }

}