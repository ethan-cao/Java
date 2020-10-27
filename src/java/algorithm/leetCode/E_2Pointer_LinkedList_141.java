package algorithm.leetCode;

/*
Given a linked list, determine if it has a cycle in it.
Use an integer pos which represents the position (0-indexed) in the linked list where tail (the last one) connects to
If pos is -1, then there is no cycle in the linked list.

pos is just used to reference the next node, since it is not possible to represent list in a array

### Example
head = [3,2,0,-4], pos = 1 --->  true
There is a cycle in the linked list, where tail connects to the second node (2)

head = [1,2], pos = 0 --->  true
There is a cycle in the linked list, where tail connects to the first node (0)

head = [1], pos = -1  --->  false
There is no cycle in the linked list (-1)

*/

public class E_2Pointer_LinkedList_141 {

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

    // cycle detection: Floyd’s algorithm
    // Space: O(1), 0ms
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head.next;

        while (slowPointer != null && fastPointer != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next == null ? null : fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }
}