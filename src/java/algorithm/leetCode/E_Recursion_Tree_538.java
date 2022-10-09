package algorithm.leetCode;

/*
Given a Binary Search Tree (BinarySearchTree), convert it to a Greater Tree
such that every key of the original BinarySearchTree is changed to
the original key plus sum of all keys greater than the original key in BinarySearchTree.

### Example
      5         ->        18
    /   \               /   \
   2     13           20      13

*/
public class E_Recursion_Tree_538 {

    public static void main(String... args) {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(13);

        n1.left = n2;
        n1.right = n3;

        System.out.println(n1);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 0ms
    public static TreeNode convertBST(TreeNode root) {
        updateValue(root, 0);
        return root;
    }

    // reversed inOrder traversal : right --> root --> left.
    private static int updateValue(TreeNode node, int sum) {
        if (node == null) {
            return sum;
        }

        int right = updateValue(node.right, sum);
        node.val += right;
        int left = updateValue(node.left, node.val);

        return left;
    }
}

