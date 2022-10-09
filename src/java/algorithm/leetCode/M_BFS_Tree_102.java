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
            int levelNodeCount = queue.size();

            for (int i = 0; i < levelNodeCount; ++i) {
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

    // 0ms
    // DFS, using DFS preorder to implement BFS
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();

        visit(root, 0, traversal);

        return traversal;
    }

    public void visit(TreeNode node, int depth, List<List<Integer>> traversal) {
        if (node == null) {
            return;
        }

        if (depth == traversal.size()) {
            traversal.add(new ArrayList<>());
        }

        traversal.get(depth).add(node.val);
        visit(node.left, depth + 1, traversal);
        visit(node.right, depth + 1, traversal);
    }

}