package algorithm.leetCode;

/*
Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

### Example
   1
 /   \
2     3
 \
  5
Output: ["1->2->5", "1->3"]
Explanation: All root-to-leaf paths are: 1->2->5, 1->3

*/

import java.util.*;

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

    // 1ms
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        reachLeaf(root, paths, sb);

        return paths;
    }

    private static void reachLeaf(TreeNode node, List<String> paths, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (sb.length() == 0) {
            sb.append(node.val);
        } else {
            sb.append("->").append(node.val);
        }

        if (node.left == null && node.right == null) {
            paths.add(sb.toString());
            return;
        }

        reachLeaf(node.left, paths, new StringBuilder(sb));
        reachLeaf(node.right, paths, new StringBuilder(sb));
    }

    private static List<String> binaryTreePaths1(TreeNode root) {
        List<String> paths = new ArrayList<>();

        if (root == null) {
            return paths;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        Deque<String> path = new ArrayDeque<>();
        path.offer("");

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            String pathNode = path.poll();

            if (node.left == null && node.right == null) {
                paths.add(pathNode + node.val);
            }

            if (node.left != null) {
                queue.offer(node.left);
                path.offer(pathNode + node.val + "->");
            }

            if (node.right != null) {
                queue.offer(node.right);
                path.offer(pathNode + node.val + "->");
            }
        }

        return paths;
    }
}

