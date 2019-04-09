package leetCode;

/*
Given a Binary Search Tree (BST), convert it to a Greater Tree
such that every key of the original BST is changed to
the original key plus sum of all keys greater than the original key in BST.

### Example
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
[5, 2, 13] --> [18,20,13]

18 = 5 + 13
20 = 2 + 18

[13,11,14] --> [27,38,14]
[5,2,13,1,3,11,14] --> [43,48,27,49,46,38,14]

### Condition

### Essential problem

### Corner case

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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}