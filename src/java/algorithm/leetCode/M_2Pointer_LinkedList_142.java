package algorithm.leetCode;

/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
To represent a cycle in the given linked list,
we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
If pos is -1, then there is no cycle in the linked list.
Note: Do not modify the linked list.

### Example
head = [3,2,0,-4], pos = 1 --> tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

head = [1,2], pos = 0 --> tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

head = [1], pos = -1 --> no cycle

*/

public class M_2Pointer_LinkedList_142 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Space: O(1), 0ms
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (slowPointer != null && fastPointer!= null ) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next == null ? null : fastPointer.next.next ;

            // if meet, there is cycle
            if (slowPointer == fastPointer) {
                ListNode current = head;

                while (current != slowPointer) {
                    current = current.next;
                    slowPointer = slowPointer.next;
                }

                return current;
            }

        }

        return null;
    }

}
