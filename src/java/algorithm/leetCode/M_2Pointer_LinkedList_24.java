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

    // Two Pointer
    // Time: O(N), Space: O(1), 0ms
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode newHead = head.next == null ? head : head.next;

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

    // Two Pointer, 0ms
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;

        ListNode odd = head;
        ListNode even = head.next;

        while (odd != null && even != null) {
            ListNode nextOdd = even.next;
            ListNode nextEven = nextOdd == null ? null : nextOdd.next;

            even.next = odd;
            odd.next = nextEven == null ? nextOdd : nextEven;

            odd = nextOdd;
            even = nextEven;
        }

        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return swap(head, head.next);
    }

    // Time:   Space: O(N), 0ms
    private ListNode swap(ListNode current, ListNode next) {
        if (current == null || next == null) {
            return current;
        }

        current.next = swap(next.next, next.next == null ? null : next.next.next);
        next.next = current;

        return next;
    }

}