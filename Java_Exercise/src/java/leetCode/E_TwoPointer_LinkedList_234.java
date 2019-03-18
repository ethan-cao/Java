package leetCode;

/*
Given a singly linked list, determine if it is a palindrome.

### Example
1->2   -->  false
1->2->2->1 --> true
1->2->3->2->1 --> true

### Condition

### Essential problem

### Corner case

*/

// Also 252 anagram

import java.util.ArrayDeque;
import java.util.Deque;

public class E_TwoPointer_LinkedList_234 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(isPalindrome(node1));

    }

    public static class ListNode {
        int value;
        ListNode next;

        ListNode(int x) {
            value = x;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(head.value);

        ListNode node = head.next;

        while (node.next != null) {

            if (node.value == stack.pop()) {
                stack.pop();
            } else {
                stack.push(node.value);
            }

            node = node.next;
        }

        return stack.isEmpty();
    }
}
