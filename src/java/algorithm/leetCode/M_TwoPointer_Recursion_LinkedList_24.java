package algorithm.leetCode;

/*
Given a linked list, swap every two adjacent nodes and return its head.
You may not modify the values in the list's nodes, only nodes itself may be changed.

### Example
1->2->3->4 ---> 2->1->4->3

*/

public class M_TwoPointer_Recursion_LinkedList_24 {

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
    // Time: O(N), Space: O(1)
    public static ListNode swapPairs(ListNode head) {
        ListNode newHead = head;

        if (newHead == null || newHead.next == null) {
            return newHead;
        }

        // swap the 1st and 2nd node
        newHead = head.next;
        head.next = head.next.next;
        newHead.next = head;

        // swap the rest
        ListNode currentNode = newHead;
        ListNode nextNode = currentNode.next;

        while (currentNode.next.next != null && nextNode.next.next != null) {
            ListNode previousNode = nextNode;
            currentNode = currentNode.next.next;
            nextNode = nextNode.next.next;
            ListNode nextNextNode = nextNode.next;

            nextNode.next = currentNode;
            currentNode.next = nextNextNode;
            previousNode.next = nextNode;

            // !!! reset current and next after swapping
            ListNode temp = currentNode;
            currentNode = nextNode;
            nextNode = temp;
        }

        return newHead;
    }

    // swapPairs() simplified
    public ListNode swapPairs1(ListNode head) {
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;

        ListNode currentNode = virtualHead;

        while (currentNode.next != null && currentNode.next.next != null) {
            ListNode node1 = currentNode.next;
            ListNode node2 = currentNode.next.next;

            currentNode.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            currentNode = node1;
        }

        return virtualHead.next;
    }

    // Recursion
    // Time:   Space: O(N)
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode nextNode = head.next;

        head.next = swapPairs2(head.next.next);
        nextNode.next = head;

        return nextNode;
    }

}
