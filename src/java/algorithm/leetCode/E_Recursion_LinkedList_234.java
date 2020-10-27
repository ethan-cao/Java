package algorithm.leetCode;

/*
Given a singly linked list, determine if it is a palindrome.

### Example
1->2   -->  false
1->2->2->1 --> true
1->2->3->2->1 --> true

*/

import java.util.ArrayDeque;
import java.util.Deque;

// Also 252 anagram
public class E_Recursion_LinkedList_234 {

    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(2);
//        ListNode node4 = new ListNode(1);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(2);
//        ListNode node5 = new ListNode(1);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;

        System.out.println(isPalindrome(node1));
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }

    // Stack
    // Time: O(N), 3ms, Space: O(N)
    public static boolean isPalindrome0(ListNode head) {
        if (head == null) {
            return true;
        }

        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        ListNode pointer1 = head;
        ListNode pointer2 = stack.pop();

        while (pointer1 != pointer2) {
            if (pointer1.val != pointer2.val) {
                return false;
            }

            pointer1 = pointer1.next;
            pointer2 = stack.isEmpty() ? null : stack.pop();
        }

        return true;
    }

    // Recursive approach, need the instance member root
    // recursive algorithm always takes O(n) space
    // 1ms
    static ListNode root;

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        if (root == null) {
            root = head;
        }

        boolean isPalindrome = isPalindrome(head.next);
        isPalindrome &= (root.val == head.val);

        root = root.next;

        return isPalindrome;
    }

    // reversing the 2nd half and compare the two halves
    // this modifies input data structure, not recommended in practice
    public static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode pointer1 = head;
        ListNode pointer2 = head;
        // 1      -> 1 -> 2 -> 1 -> null
        // p1 p2

        while (pointer2 != null && pointer2.next != null) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next.next;
            // pointer2 moves 2 slots each time, so it reaches the end, while pointer1 reaches the middle
        }
        // 1 -> 1 -> 2 -> 1 -> null
        //           p1        p2


        // since it is not possible to reverse and move pointer2 to the beginning of 2nd half list, use pointer1
        // because pointer2 is already in the end, cannot travel backwards

        // move pointer2 to the beginning of left list
        pointer2 = head;

        // reverse the 2nd half list and move pointer1 to the beginning to the right list
        // pointer1 now points to the 1st element in the right half list
        ListNode previous = null;
        while (pointer1 != null) {
            ListNode next = pointer1.next; // preserve the next one

            pointer1.next = previous;
            previous = pointer1;

            pointer1 = next;
        }
        pointer1 = previous;

        // 1 -> 1    2 <- 1
        // p2             p1

        // compare 2 list
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.val != pointer2.val) {
                return false;
            }

            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return true;
    }
}
