package algorithm.leetCode;

/*
Given a singly linked list, group all odd nodes together followed by the even nodes.
Please note here we are talking about the node number and not the value in the nodes.

do it in place, with time: O(N) and space: O(1)

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on

### Example
1->2->3->4->5->NULL -> 1->3->5->2->4->NULL
2->1->3->5->6->4->7->NULL -> 2->3->6->7->1->5->4->NULL

*/

public class M_TwoPointer_Recursion_LinkedList_328 {

    public static void main(String... args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        oddEvenList1(node1);

        ListNode.printList(node1);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static void printList(ListNode node) {
            while (node != null) {
                System.out.println(node.val);
                node = node.next;
            }
        }
    }

    // Time: O(N), Space: O(1)
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode evenHead = head.next;

        ListNode oddTail = head;
        ListNode evenTail = head.next;

        while (oddTail.next != null && evenTail.next != null) {
            oddTail.next = oddTail.next.next;
            evenTail.next = evenTail.next.next;

            oddTail = oddTail.next;
            evenTail = evenTail.next;
        }

        oddTail.next = evenHead;

        return head;
    }

    // Recursive, not O(1) space
    public static ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return splitList(head, head, head.next);
    }

    private static ListNode splitList(ListNode head, ListNode oddTail, ListNode evenTail) {
        if (evenTail == null || evenTail.next == null) {
            return head;
        }

        ListNode nextOdd = evenTail.next;
        ListNode nextEven = nextOdd.next;

        nextOdd.next = oddTail.next;

        oddTail.next = nextOdd;
        evenTail.next = nextEven;

        return splitList(head, nextOdd, nextEven);
    }

}