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

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 1ms
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        StringBuilder tracker = new StringBuilder();

        collect(paths, tracker, root);

        return paths;
    }

    private void collect(List<String> paths, StringBuilder tracker, TreeNode node) {
        // when this node is leave
        if (node.left == null && node.right == null) {
            tracker.append(node.val);
            paths.add(tracker.toString());
            return;
        }

        tracker.append(node.val).append("->");

        if (node.left != null) {
            // backtrack, since we create a new trakcer, it decouple left path from right path
            StringBuilder trackerLeft = new StringBuilder(tracker);
            collect(paths, trackerLeft, node.left);
        }

        if (node.right != null) {
            StringBuilder trackerRight = new StringBuilder(tracker);
            collect(paths, trackerRight, node.right);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS
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
