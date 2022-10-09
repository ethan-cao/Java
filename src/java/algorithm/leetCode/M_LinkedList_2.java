package algorithm.leetCode;

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

### Example
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

*/

public class M_LinkedList_2 {

    public static void main(String... args) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n;

        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(4);
        n4.next = n5;
        n5.next = n6;

        ListNode sum = addTwoNumbers1(n1, n4);
        while (sum != null) {
            System.out.println(sum.val);
            sum = sum.next;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 1ms
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode sumHead = new ListNode(0);

        ListNode sum = sumHead;
        int nextVal = 0;

        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            int val = (val1 + val2 + nextVal) % 10;
            nextVal = (val1 + val2 + nextVal) / 10;

            sum.next = new ListNode(val);

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            sum = sum.next;
        }

        if (nextVal != 0) {
            sum.next = new ListNode(nextVal);
        }

        return sumHead.next;
    }
}
