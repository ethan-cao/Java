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

### Condition

### Essential problem

### Corner case

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

    // this is not acceptable answer since l1 and l2 could be even larger than long
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        long num1 = getValue(l1);
        long num2 = getValue(l2);
        long sum = num1 + num2;

        ListNode head = null;
        ListNode node = null;

        while (sum > 0) {
            int remainder = (int) (sum % 10);

            if (node == null) {
                node = new ListNode(remainder);
                head = node;
            } else {
                node.next = new ListNode(remainder);
                node = node.next;
            }

            sum /= 10;
        }

        return head;
    }

    private static long getValue(ListNode node) {
        StringBuilder sb = new StringBuilder();

        while (node != null) {
            sb.append(node.val);
            node = node.next;
        }

        return Long.valueOf(sb.reverse().toString());
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode node1 = l1;
        ListNode node2 = l2;

        ListNode node = null;
        ListNode head = null;

        int carry = 0;

        while (node1 != null && node2 != null) {
            int sum = node1.val + node2.val + carry;
            carry = sum / 10;
            sum = sum % 10;

            if (node == null) {
                node = new ListNode(sum);
                head = node;
            } else {
                node.next = new ListNode(sum);
                node = node.next;
            }

            node1 = node1.next;
            node2 = node2.next;
        }

        ListNode remainingNode = node1 == null ? node2 : node1;
        while (remainingNode != null) {
            int value = remainingNode.val % 10 + carry;
            carry = value / 10;
            value = value % 10;

            node.next = new ListNode(value);
            node = node.next;

            remainingNode = remainingNode.next;
        }

        if (carry != 0) {
            node.next = new ListNode(carry);
        }

        return head;
    }
}
