package algorithm.leetCode;

/*
Given the root of a binary tree, return the postorder traversal of its nodes' values.
*/

import java.util.*;

public class M_Stack_Tree_145 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // DFS, Recursive 0ms
    // Time O(n), Space: O(n)
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();

        visit(root, traversal);

        return traversal;
    }

    private void visit(TreeNode node, List<Integer> traversal) {
        if (node == null) {
            return;
        }

        visit(node.left, traversal);
        visit(node.right, traversal);
        traversal.add(node.val);
    }

    // Iterative, Stack
    // Time O(n), Space: O(n), 0ms
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode current = root;

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
            TreeNode node = stack.pop();
            traversal.add(node.val); // node is added after both its children are visited

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

}