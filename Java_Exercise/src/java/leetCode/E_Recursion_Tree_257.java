package leetCode;

import java.util.List;

/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

### Example

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3


### Condition

### Essential problem

### Corner case

*/
public class E_Recursion_Tree_257 {

    public static void main(String... args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n1.left = n2;
        n1.right = n3;
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        return null;
    }

}

