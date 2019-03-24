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
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        deleteDuplicates(node1);
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

    public static ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new LinkedHashSet<>();

        ListNode node = head;
        while (node != null) {
            set.add(node.val);
            node.next = node;
        }

        node = head;
        set.remove(head.val);

        for (Integer i : set){
            node.next = new ListNode(i);
            node = node.next;
        }

        return node;
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        Set<Integer> set = new HashSet<>();
        set.add(head.val);

        ListNode node = head;
        // manually add a duplicate value to the end

        while (node != null && node.next != null) {

            if (set.contains(node.next.val)) {
                node.next = node.next.next;
            } else {
                set.add(node.next.val);
            }

            node = node.next;
        }

        if (node != null && set.contains(node.val)) {

        }

        return head;
    }
}
