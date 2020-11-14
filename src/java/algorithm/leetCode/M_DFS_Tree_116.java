package algorithm.leetCode;

/*
You are given a perfect binary tree where all leaves are on the same level
and every parent has two children. The binary tree has the following definition:
Populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
The number of nodes in the given tree is less than 4096.
-1000 <= node.val <= 1000

### Example
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
[1,2,3,4,5,6,7] -> [1,#,2,3,#,4,5,6,7,#]

*/

import java.util.*;

public class M_DFS_Tree_116 {

    class Node {
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

    // DFS, iterative, 0ms
    // Time: O(N)   Space: O(1)
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Node currentDepth = root;
        while (currentDepth != null && currentDepth.left != null) {

            Node current = currentDepth;
            while (current != null) {
                current.left.next = current.right;
                current.right.next = current.next == null ? null : current.next.left;

                current = current.next;
            }

            currentDepth = currentDepth.left;
        }

        return root;
    }

    // DFS, preorder, recursive, 0ms
    // Time: O(N)
    public Node connect1(Node root) {
        if (root == null) {
            return root;
        }

        if (root.left != null) {
            root.left.next = root.right;
        }

        if (root.right != null) {
            root.right.next = root.next == null ? null : root.next.left;
        }

        connect1(root.left);
        connect1(root.right);

        return root;
    }

    // BFS 2ms, not O(1) space
    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Node previous = queue.peekFirst();

            for (int i = 0; i < size; ++i) {
                Node node = queue.poll();

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