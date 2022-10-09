package algorithm.leetCode;

/*
Given the root of a binary tree, return the preorder traversal of its nodes' values.
*/

import java.util.*;

public class M_Stack_Tree_144 {

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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();

        visit(root, traversal);

        return traversal;
    }

    private void visit(TreeNode node, List<Integer> traversal) {
        if (node == null) {
            return;
        }

        traversal.add(node.val);
        visit(node.left, traversal);
        visit(node.right, traversal);
    }

    // Iterative, Stack
    // Time O(n), Space: O(n), 0ms
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                // current is the left child in previous iteration
                // current is also the parent in this iteration
                traversal.add(current.val);   // !!! node is added when it is visited before it children
                stack.push(current);

                current = current.left;
            }

            // current.left is null now, finished visiting parent and its left child

            current = stack.pop();
            current = current.right;
        }

        return traversal;
    }

}