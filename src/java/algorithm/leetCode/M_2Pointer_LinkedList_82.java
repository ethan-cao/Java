package algorithm.leetCode;

/*
Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.

### Example
1->2->3->3->4->4->5 ---> 1->2->5
1->1->1->2->3       ---> 2->3

*/

public class M_2Pointer_LinkedList_82 {

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

        ListNode head = deleteDuplicates(n1);

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

    // Time: O(N), Space: O(1)
    // go through the list and remove duplicates
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode virtualHead = new ListNode(head.val - 1);
        virtualHead.next = head;

        ListNode current = virtualHead; // no duplicate until current (inclusive)
        ListNode next = current.next;

        while (next != null && next.next != null) {
            boolean skip = false;

            while (next.next != null && next.val == next.next.val) {
                skip = true;
                next.next = next.next.next;
            }

            if (skip) {
                current.next = next.next;
            } else {
                current = next;
            }

            next = current == null ? null : current.next;
        }

        return virtualHead.next;
    }

    // Time: O(N), Space: O(1)
    // go through the list and add unique to the new list
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }

        // !!! guarantee virtual head has a different val than head
        ListNode virtualHead = new ListNode(head.val - 1);
        virtualHead.next = head;

        ListNode current = virtualHead; // no duplicate until current
        ListNode next = current.next;

        // add unique to the new list
        ListNode node = virtualHead;

        while (next != null && next.next != null) {

            if (next.val != current.val && next.val != next.next.val) {
                node.next = next;
                node = node.next;
            }

            current = next;
            next = current.next;
        }

        if (current.val != next.val) {
            node.next = next;
            node = node.next;
        }

        node.next = null;

        return virtualHead.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        return getUniqueNodes(null, head);
    }

    // remove duplicate nodes from current node
    private static ListNode getUniqueNodes(ListNode previous, ListNode current) {
        if (current == null) {
            return null;
        }

        if (previous != null && current.val == previous.val || current.next != null && current.val == current.next.val) {
            return getUniqueNodes(current, current.next);
        } else {
            current.next = getUniqueNodes(current, current.next);
            return current;
        }
    }
}
