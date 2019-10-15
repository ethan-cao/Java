package algorithm.leetCode;

/*
A linked list is given such that each node contains an additional random pointer
which could point to any node in the list or null.

Return a deep copy of the list.

You must return the copy of the given head as a reference to the cloned list.


### Example
Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.

*/

import java.util.HashMap;
import java.util.Map;

public class M_LinkedList_138 {

    public static void main(String... args) {
//        Node n1 = new Node(1)
    }

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node headCopy = replicate(head);

        Map<Node, Node> map = new HashMap<>();
        map.put(head, headCopy);

        Node previousCopy = headCopy;
        Node currentCopy;

        // copy val and next pointer
        Node current = head.next;
        while (current != null) {
            currentCopy = replicate(current);
            previousCopy.next = currentCopy;

            previousCopy = currentCopy;

            map.put(current, currentCopy);

            current = current.next;
        }


        // copy random pointer
        currentCopy = headCopy;
        current = head;

        while (current != null) {
            currentCopy.random = map.get(current.random);

            currentCopy = currentCopy.next;
            current = current.next;
        }

        return headCopy;
    }

    private static Node replicate(Node node) {
        Node copy = new Node();
        copy.val = node.val;
        return copy;
    }

    // Concise version
    public static Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }

        // Node use Object.hashCode and Object.equal
        // Object.hashCode() / Object.equals() return true for objects with same address
        // use HashMap to make lookup linear, similar to Q1 twoSum
        Map<Node, Node> originToDuplicate = new HashMap<>();

        Node current = head;
        while (current != null) {
            Node duplicate = replicate(current);
            originToDuplicate.put(current, duplicate);

            current = current.next;
        }

        Node headCopy = originToDuplicate.get(head);

        current = head;
        Node currentCopy = headCopy;

        while (current != null) {
            currentCopy.next = originToDuplicate.get(current.next);
            currentCopy.random = originToDuplicate.get(current.random);

            current = current.next;
            currentCopy = currentCopy.next;
        }

        return headCopy;
    }
}
