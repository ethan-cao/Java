package algorithm.leetCode;
/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
Bonus points if you could solve it both recursively and iteratively.

### Example
    1
   / \
  2   2
 / \ / \
3  4 4  3  -> true

    1
   / \
  2   2
   \   \
   3    3  -> false
*/

import java.util.Stack;

public class E_Recursion_Tree_101 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Recursive, 0ms
    public static boolean isSymmetric(TreeNode root) {
        return root == null ? true : isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    // Iterative, use stack to mimic  call stack
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }

        // Deque does not allow null elements
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();

            if (left == null && right == null) {
                continue;
            }

            if (right == null || left == null || right.val != left.val) {
                return false;
            }

            stack.push(left.left);
            stack.push(right.right);
            stack.push(right.left);
            stack.push(left.right);
        }

        return true;
    }
}

