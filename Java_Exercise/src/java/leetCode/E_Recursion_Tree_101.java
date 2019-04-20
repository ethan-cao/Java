package leetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

Bonus points if you could solve it both recursively and iteratively.

### Example
[1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3

[1,2,2,null,3,null,3] is not symmetric
    1
   / \
  2   2
   \   \
   3    3

### Condition

### Essential problem

### Corner case

*/
public class E_Recursion_Tree_101 {

    public static void main(String... args) {
    }

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


    // Non-recursive
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }

        // Deque does not allow null elements
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();

            if (node1 == null && node2 == null) {
                continue;
            }

            if (node2 == null || node1 == null || node2.val != node1.val) {
                return false;
            }

            stack.push(node1.left);
            stack.push(node2.right);
            stack.push(node2.left);
            stack.push(node1.right);
        }

        return true;
    }
}

