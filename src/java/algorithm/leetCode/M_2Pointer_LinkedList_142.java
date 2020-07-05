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
Explanation: There is no cycle in the linked list.

*/

public class M_2Pointer_LinkedList_142 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // https://leetcode.com/problems/linked-list-cycle-ii/discuss/44774/Java-O(1)-space-solution-with-detailed-explanation.
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                // if meet, there is cycle
                ListNode current = head;

                while (current != slowPointer) {
                    slowPointer = slowPointer.next;
                    current = current.next;
                }

                return current;
            }
        }

        return null;
    }

}
