package algorithm.leetCode;

/*
Given a binary tree, return the level order traversal of its nodes' values.
(ie, from left to right, level by level).

### Example
[3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
-> [ [3],  [9,20],  [15,7] ]

*/

import java.util.*;

public class M_BFS_Tree_102 {

    public static void main(String... args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // BFS
    // Time O(), Space: O()
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();

        if (root == null) {
            return traversal;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> levelTraversal = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                TreeNode currentNode = queue.poll();
                levelTraversal.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            traversal.add(levelTraversal);
        }

        return traversal;
    }

    // DFS, using DFS preorder to implement BFS
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();
        preorderVisit(root, 0, traversal);
        return traversal;
    }

    private static void preorderVisit(TreeNode node, int depth, List<List<Integer>> traversal) {
        if (node == null) {
            return;
        }

        if (depth >= traversal.size()) {
            traversal.add(new ArrayList<>());
        }

        traversal.get(depth).add(node.val);
        preorderVisit(node.left, depth + 1, traversal);
        preorderVisit(node.right, depth + 1, traversal);
    }

}