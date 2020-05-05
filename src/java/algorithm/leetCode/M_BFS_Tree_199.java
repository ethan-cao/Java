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

import java.util.List;

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

    // BFS
    // Time O(), Space: O()
    public List<Integer> rightSideView(TreeNode root) {
        return null;
    }

}