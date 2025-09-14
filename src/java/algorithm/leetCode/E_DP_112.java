package algorithm.leetCode;

/*

Given the root of a binary tree and an integer targetSum, 
return true if the tree has a root-to-leaf path 
such that adding up all the values along the path equals targetSum.

*/

public class E_DP_112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return search(root, targetSum);
    }

   boolean search(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }
        
        if (node.left == null && 
            node.right == null && 
            sum - node.val == 0
        ) {
            return true;
        }
        
        if  (search(node.left, sum - node.val) ) {
            return  true;
        }

        if  (search(node.right, sum - node.val) ) {
            return  true;
        }
        
        return false;
    }

}
