package algorithm.leetCode;

/*
Sort a linked list in O(NlogN) time and O(N) space complexity.

### Example
4->2->1->3  -->   1->2->3->4
-1->5->3->4->0 --> -1->0->3->4->5

*/

import java.util.*;

public class M_Sort_LinkedList_148 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // bottom-up mergesort: https://www.youtube.com/watch?v=ts1Y5zhuuHA   https://www.youtube.com/watch?v=M1TwY0nsTZA
    // Time: O(NlogN), Space: O(1)
    public static ListNode sortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // count list size
        int N = 0;
        while (head != null) {
            head = head.next;
            N++;
        }

        // split and merge for logN times
        for (int n = 1; n < N; n = n * 2) {
            ListNode previous = dummyHead;
            ListNode current = dummyHead.next;

            while (current != null) {
                // split the list into 2 parts
                ListNode left = current;         // head of left list
                ListNode right = split(left, n); // head of right list

                current = split(right, n);
                previous = merge(left, right, previous);
            }
        }

        return dummyHead.next;
    }

    // split a list starts from node into 2 lists, list 1 start from node with size, and return head of list 2
    private static ListNode split(ListNode node, int size) {
        if (node == null) {
            return null;
        }

        for (int i = 1; node.next != null && i < size; ++i) {
            node = node.next;
        }

        ListNode right = node.next;
        node.next = null;

        return right;
    }

    // merge sorted list1 starting from left and sorted list starting from right
    private static ListNode merge(ListNode left, ListNode right, ListNode previous) {
        ListNode current = previous;

        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        if (left != null) {
            current.next = left;
        } else if (right != null) {
            current.next = right;
        }

        while (current.next != null) {
            current = current.next;
        }

        return current;
    }

    // 3-6ms
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode previous = null;

        while (fast != null && fast.next != null) {
            previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        previous.next = null;

        ListNode dummyHead = new ListNode(0);
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        ListNode current = dummyHead;

        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        if (left == null) {
            current.next = right;
        } else {
            current.next = left;
        }

        return dummyHead.next;
    }

}
