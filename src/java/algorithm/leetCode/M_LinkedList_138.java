package algorithm.leetCode;

/*
A linked list is given such that each node contains an additional random pointer
which could point to any node in the list or null.
Return a deep copy of the list.
You must return the copy of the given head as a reference to the cloned list.

### Example
https://leetcode.com/problems/copy-list-with-random-pointer/

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

*/

import java.util.*;

public class M_LinkedList_138 {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int _val) {
            val = _val;
            next = null;
            random = null;
        }
    }

    // Time O(N) 0ms
    public static Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }

        // Node use Object.hashCode and Object.equal
        // Object.hashCode() / Object.equals() return true for objects with same address
        Map<Node, Node> originToDuplicate = new HashMap<>();

        Node current = head;
        while (current != null) {
            Node duplicate = new Node(current.val);
            originToDuplicate.put(current, duplicate);

            current = current.next;
        }

        Node headCopy = originToDuplicate.get(head);

        current = head;
        Node currentCopy = headCopy;

        while (current != null) {
            currentCopy.next = originToDuplicate.get(current.next); // this can be done in 1st loop
            currentCopy.random = originToDuplicate.get(current.random);

            current = current.next;
            currentCopy = currentCopy.next;
        }

        return headCopy;
    }
}
