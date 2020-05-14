package algorithm.leetCode;

/*
Given a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.

### Example
[1,2,3,null,5,null,4] -> [1, 3, 4]
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

*/

import java.util.*;

public class M_BFS_Tree_199 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // BFS, 1ms
    // Time O(), Space: O()
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelNodeCount = queue.size();

            for (int i = 0; i < levelNodeCount; ++i) {
                TreeNode node = queue.poll();

                if (i == levelNodeCount - 1) {
                    result.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return result;
    }

    // BFS, 0ms
    // Time O(), Space: O()
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> view = new ArrayList<>();

        visit(root, 0, view);

        return view;
    }

    public void visit(TreeNode node, int depth, List<Integer> view) {
        if (node == null) {
            return;
        }

        // makes sure the 1st element of that level will be added to the result list
        if (depth == view.size()) {
            view.add(node.val);
        }

        // NOT standard pre-order traverse. It checks the RIGHT node first and then the LEFT
        visit(node.right, depth + 1, view);
        visit(node.left, depth + 1, view);

        // if reverse the visit order, that is first LEFT and then RIGHT, return the left view of the tree.
    }
}