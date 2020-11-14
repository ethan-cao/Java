package algorithm.leetCode;

/*
Given a binary tree
Populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

The number of nodes in the given tree is less than 6000.
-100 <= node.val <= 100

### Example
https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
[1,2,3,4,5,null,7] -> [1,#,2,3,#,4,5,7,#]

*/

import java.util.*;

public class M_DFS_Tree_117 {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    // DFS, iterative, 0m,  level-order traversal
    // Time: O(N)   Space: O(1)
    public Node connect(Node root) {
        Node previous = null;
        Node current = null;
        Node parent = root;

        while (parent != null) {
            previous = null;
            current = parent;
            parent = null;

            while (current != null) {

                if (current.left != null) {
                    if (previous == null) {
                        parent = current.left;
                    } else {
                        previous.next = current.left;
                    }

                    previous = current.left;
                }

                if (current.right != null) {
                    if (previous == null) {
                        parent = current.right;
                    } else {
                        previous.next = current.right;
                    }

                    previous = current.right;
                }

                current = current.next;
            }
        }

        return root;
    }

    // BFS 1ms, not O(1) space
    public M_DFS_Tree_116.Node connect2(M_DFS_Tree_116.Node root) {
        if (root == null) {
            return root;
        }

        Deque<M_DFS_Tree_116.Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            M_DFS_Tree_116.Node previous = queue.peekFirst();

            for (int i = 0; i < size; ++i) {
                M_DFS_Tree_116.Node node = queue.poll();

                if (i != 0) {
                    previous.next = node;
                    previous = node;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }
}