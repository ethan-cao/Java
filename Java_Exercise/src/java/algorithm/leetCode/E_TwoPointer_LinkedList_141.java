package algorithm.leetCode;

/*
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos
which represents the position (0-indexed) in the linked list
where tail connects to. If pos is -1, then there is no cycle in the linked list.

pos is just used to illustrate where the last node connects to,
since it it not possible to represent it in a array

### Example
head = [3,2,0,-4], pos = 1 --->  true
There is a cycle in the linked list, where tail connects to the second node (2)

head = [1,2], pos = 0 --->  true
There is a cycle in the linked list, where tail connects to the first node (0)

head = [1], pos = -1  --->  false
There is no cycle in the linked list (-1)

### Condition
Can you solve it using O(1) (i.e. constant) memory?

### Essential problem

### Corner case

*/


public class E_TwoPointer_LinkedList_141 {
    public static void main(String... args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Floyd’s cycle detection
    public static boolean hasCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        while (p2 != null && p2.next != null) {
            if (p1 == p2) {
                return true;
            }

            p1 = p1.next;
            p2 = p2.next.next;
        }

        return false;
    }
}
