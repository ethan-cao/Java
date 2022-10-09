package algorithm.leetCode;

/*
Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
a height-balanced binary tree is defined as a binary tree
in which the depth of the two subtrees of every node never differ by more than 1.

*/

import java.util.*;

public class M_Tree_109 {

    //Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Definition for a binary tree node.
    class TreeNode {
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


    // Time: O(N)  Space: O(logN)
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> values = new ArrayList<>();

        while (head != null) {
            values.add(head.val);
            head = head.next;
        }

        return buildBST(0, values.size() - 1, values);
    }

    private TreeNode buildBST(int left, int right, List<Integer> values) {
        if (left > right) {
            return null;
        }

        // use middle as root to assure the BST is balanced !!!
        int middle = left + (right - left) / 2;
        TreeNode node = new TreeNode(values.get(middle));

        node.left = buildBST(left, middle - 1, values);
        node.right = buildBST(middle + 1, right, values);

        return node;
    }

    // Time: O(n logn) since traverse the sub-list in each recursive call.
    public TreeNode sortedListToBST1(ListNode head) {
        return buildBST(head, null);
    }

    // split the list to left and right half, tail is the lastNode.next in each half
    private TreeNode buildBST(ListNode head, ListNode tail) {
        if (head == null || head == tail) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // now slow points to the middle

        TreeNode node = new TreeNode(slow.val);
        node.left = buildBST(head, slow);
        node.right = buildBST(slow.next, tail);

        return node;
    }
}