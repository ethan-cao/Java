package algorithm.leetCode;

/*
Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.

### Example
1->2->3->3->4->4->5 ---> 1->2->5
1->1->1->2->3       ---> 2->3

*/

public class M_LinkedList_82 {

    public static void main(String... args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n33 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n44 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n33;
        n33.next = n4;
        n4.next = n44;
        n44.next = n5;

//        printList(n1);

        ListNode head = deleteDuplicates2(n1);

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

    // Time: O(N), Space: O(1), 1ms
    // go through the list and remove duplicates
    public static ListNode deleteDuplicates(ListNode head) {
        // !!! guarantee virtual head has a different val than head
        ListNode dummyHead = new ListNode(head.val - 1);
        dummyHead.next = head;

        ListNode current = dummyHead; // no duplicate until current (inclusive)
        ListNode next = current.next;

        // node need to check current is null. next is null first
        while (next != null) {
            boolean hasDuplicate = false;

            while (next.next != null && next.val == next.next.val) {
                hasDuplicate = true;
                next.next = next.next.next;
            }

            if (hasDuplicate) {
                current.next = next.next;
            } else {
                current = next;
            }

            next = current.next;
        }

        return dummyHead.next;
    }

    // 0ms
    // compare the node with its previous and next node
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode(head.val - 1);
        dummyHead.next = head;

        dummyHead.next = getNextUniqueNode(dummyHead, head);

        return dummyHead.next;

        // alternative
        // return getNextUniqueNode(new ListNode(head.val-1), head);
    }

    // get the next unique node for the current node
    private static ListNode getNextUniqueNode(ListNode current, ListNode next) {
        if (next == null) {
            return null;
        }

        // node need to check current is null. next is null first
        if (next.val != current.val && (next.next == null ? true : next.val != next.next.val)) {
            // next is unique, current.next refer to next, no change needed
            next.next = getNextUniqueNode(next, next.next);
            return next;
        } else {
            // next is not unique, the next unique node for node current is
            // the next unique node for node next
            return getNextUniqueNode(next, next.next);
        }
    }

    // Time: O(N), Space: O(1), 1ms
    // go through the list and add unique to the new list
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        // !!! guarantee virtual head has a different val than head
        ListNode dummyHead = new ListNode(head.val - 1);
        dummyHead.next = head;

        ListNode current = dummyHead; // no duplicate until current
        ListNode next = current.next;

        ListNode uniqueNode = dummyHead; // points to unique nodes

        // no need to check current/next as null, next.next reaches null first
        while (next != null) {

            // check if next has duplicate value
            if (next.val != current.val && (next.next == null ? true : next.val != next.next.val)) {
                uniqueNode.next = next;
                uniqueNode = uniqueNode.next;
            }

            current = next;
            next = current.next;
        }

        uniqueNode.next = null; // when input is [1,1], uniqueNode is dummyHead, uniqueNode.next is still head

        return dummyHead.next;
    }

}
