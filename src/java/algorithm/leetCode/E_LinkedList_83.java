package algorithm.leetCode;

/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

### Example
1->1->2         -->  1->2
1->1->2->3->3   -->  1->2->3

*/


public class E_LinkedList_83 {

    public static void printList(ListNode node1) {
        ListNode node = node1;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        deleteDuplicates0(node1);

        printList(node1);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode deleteDuplicates0(ListNode head) {
        ListNode current = head;
        ListNode next = current == null ? null : current.next;

        while (next != null) {
            if (current.val == next.val) {
                current.next = next.next;
            } else {
                current = next;
            }

            next = current.next;
        }

        return head;
    }
}
