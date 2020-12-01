package algorithm.leetCode;

/*
Given a binary tree, return the inorder traversal of its nodes' values.
*/

import java.util.*;

public class M_Stack_Tree_94 {

    public static void main(String... args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n1.right = n2;
        n2.left = n3;

        List<Integer> traversal = inorderTraversal(n1);
        traversal.forEach(System.out::println);
    }

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
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        inOrderVisit(root, traversal);
        return traversal;
    }

    private static void inOrderVisit(TreeNode node, List<Integer> traversal) {
        if (node == null) {
            return;
        }

        inOrderVisit(node.left, traversal);
        traversal.add(node.val);
        inOrderVisit(node.right, traversal);
    }

    // Iterative, Stack, reuse call stack
    // Time O(n), Space: O(n), 0ms
    // similar to 173
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {

            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            // current.left is null now, finished visiting parent and its left child

            currentNode = stack.pop();

            traversal.add(currentNode.val);  // node is added after its left is visited before its right is visited

            currentNode = currentNode.right;
        }

        return traversal;
    }

    // Morris traversal: O(n) time and O(1) space.    114 also use Morris traversal
    public List<Integer> inorderTraversal2(TreeNode root) {
        return null;
    }
}