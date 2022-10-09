package algorithm.leetCode;

/*
Given a linked list, remove the n-th node from the end of list and return its head.
The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

### Example
head = [1,2,3,4,5], n = 2 -> [1,2,3,5]
head = [1], n = 1 -> []
head = [1,2], n = 1 -> [1]

*/

public class M_2Pointer_LinkedList_19 {

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

        ListNode head = removeNthFromEnd1(node1, 1);

        printList(head);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

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
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode virtualHead = new ListNode();
        virtualHead.next = head;

        ListNode frontPointer = virtualHead;
        ListNode backPointer = virtualHead;

        for (int i = 0; i <= n; ++i) {
            backPointer = backPointer.next;
        }

        while (backPointer != null) {
            frontPointer = frontPointer.next;
            backPointer = backPointer.next;
        }

        frontPointer.next = frontPointer.next.next;

        return virtualHead.next;
    }

    private static int idx = 0;

    // Recursion
    // Time: , Space:
    private int distance = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;

        visitNext(virtualHead, head, n);

        return virtualHead.next;
    }

    private void visitNext(ListNode previous, ListNode current, int n) {
        if (current == null) {
            return;
        }

        visitNext(current, current.next, n);
        this.distance++; // start counting after reaching the end

        if (distance == n) {
            previous.next = current.next;
        }
    }
}
