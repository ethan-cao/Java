package leetCode;

/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

### Example
1->1->2         -->  1->2
1->1->2->3->3   -->  1->2->3

### Condition

### Essential problem

### Corner case

*/

import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class E_LinkedList_83 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        deleteDuplicates0(node1);
        ListNode node = node1;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode deleteDuplicates0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;

        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        return head;
    }
}
