package algorithm.leetCode;

/*
Given a linked list, remove the n-th node from the end of list and return its head.
Given n will always be valid.

### Example
1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.

1->2, and n = 1
1

*/

public class M_TwoPointer_Recursion_LinkedList_19 {

    public static void main(String... args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);

        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        ListNode head = removeNthFromEnd(node1, 1);

        printList(head);
    }

    static class ListNode {
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

    private static int idx = 0;

    // Time: , Space:
    // Recursion
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode secondNode = head.next;
        int headVal = head.val;

        visitNext(null, head, n);

        return head.val != headVal ? secondNode : head;
    }

    private static void visitNext(ListNode previousNode, ListNode currentNode, int n) {
        if (currentNode == null) {
            return;
        }

        visitNext(currentNode, currentNode.next, n);
        idx++; // count backwards

        if (idx == n) {
            if (previousNode == null) {
                currentNode.val++;
            } else {
                previousNode.next = currentNode.next;
            }
        }
    }

    // Two Pointer
    // Time: O(N), Space: O(1)
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;

        ListNode leftPointer = virtualHead;
        ListNode rightPointer = virtualHead;

        // make distance between 2 pointers to n
        for (int i = 0; i <= n; ++i) {
            rightPointer = rightPointer.next;
        }

        // when rightPointer moves behind the last one, leftPointer.next is the one to drop
        while (rightPointer != null) {
            leftPointer = leftPointer.next;
            rightPointer = rightPointer.next;
        }

        leftPointer.next = leftPointer.next.next;

        return virtualHead.next;
    }

}