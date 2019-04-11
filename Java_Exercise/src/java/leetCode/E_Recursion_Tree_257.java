package leetCode;

import java.util.ArrayList;
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
        TreeNode n4 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();

        reachLeaf(root, paths, new StringBuilder());

        return paths;
    }

    private static void reachLeaf0(TreeNode node, List<String> path, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            path.add(node.val + "");
            return;
        }

//        if (node) {
//
//        }
    }

    // Recursive, DFS
    private static void reachLeaf(TreeNode node, List<String> path, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            sb.append(node.val);
            path.add(sb.toString());
            return;
        }

        if (node.left != null) {
            StringBuilder current = new StringBuilder(sb);
            reachLeaf(node.left, path, sb.append(node.val + "->"));
            sb = current;  // restore the sb for right node
        }

        if (node.right != null) {
            reachLeaf(node.right, path, sb.append(node.val + "->"));
        }
    }
}

