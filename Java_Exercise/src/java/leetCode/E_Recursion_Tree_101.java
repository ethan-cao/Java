package leetCode;

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

        if (left == null || right == null) {
            return false;
        }

        return (left.val == right.val) && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}

