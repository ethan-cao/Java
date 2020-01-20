package algorithm.leetCode;

/*
Given a linked list, swap every two adjacent nodes and return its head.
You may not modify the values in the list's nodes, only nodes itself may be changed.

### Example
1->2->3->4 ---> 2->1->4->3

*/

public class M_2Pointer_LinkedList_24 {

    public static void main(String... args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        ListNode head = swapPairs(n1);

        printList(head);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    // Time: O(N), Space: O(1)
    // Two Pointer, 0ms
    public static ListNode swapPairs(ListNode head) {
        ListNode newHead = null;

        if (head == null) {
            return newHead;
        }

        newHead = head.next == null ? head : head.next;

        ListNode current = head;
        ListNode next = current.next;

        while (current != null && next != null) {
            ListNode nextNext = next.next;

            next.next = current;
            current.next = (nextNext != null && nextNext.next != null) ? nextNext.next : nextNext;

            current = nextNext;
            next = current == null ? null : current.next;
        }

        return newHead;
    }

    // 0ms
    public ListNode swapPairs1(ListNode head) {
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;

        ListNode current = virtualHead;
        ListNode next = current.next;

        while (next != null && next.next != null) {
            ListNode nextNext = next.next;

            current.next = next.next;
            next.next = nextNext.next;
            nextNext.next = next;

            current = next;
            next = current.next;
        }

        return virtualHead.next;
    }

    // Recursion
    // Time:   Space: O(N)
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;

        head.next = swapPairs2(next.next);
        next.next = head;

        return next;
    }

}