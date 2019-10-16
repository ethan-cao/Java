package algorithm.leetCode;

/*
Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.

### Example
1->2->3->3->4->4->5 ---> 1->2->5
1->1->1->2->3       ---> 2->3

*/

public class M_TwoPointer_Recursion_LinkedList_82 {

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

        ListNode head = deleteDuplicates1(n1);

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

        // !!! guarantee virtual head has a different val than head
        ListNode virtualHead = new ListNode(head.val + 1);
        virtualHead.next = head;

        ListNode currentNode = virtualHead;
        ListNode left = currentNode.next;
        ListNode right = currentNode.next;

        while (left != null) {

            while (right.next != null && right.val == right.next.val) {
                right = right.next;
            }

            if (left == right) {
                currentNode = left;
                left = currentNode.next;
                right = left;
            } else {
                left = right.next;
                right = left;
                currentNode.next = left;
            }
        }

        return virtualHead.next;
    }

    // Time: O(N), Space: O(1)
    // go through the list and add unique to the new list
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;

        ListNode previousNode = virtualHead;
        ListNode currentNode = virtualHead.next;

        ListNode node = virtualHead;

        while (currentNode != null && currentNode.next != null) {
            if (currentNode.val != previousNode.val && currentNode.val != currentNode.next.val) {
                node.next = currentNode;
                node = node.next;
            }

            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        if (previousNode.val != currentNode.val) {
            node.next = currentNode;
            node = node.next;
        }
        node.next = null;

        return virtualHead.next;
    }

    //if current node is not unique, return deleteDuplicates with head.next.
    //If current node is unique, link it to the result of next list made by recursive call.
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;

        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }

    public static ListNode deleteDuplicates3(ListNode head) {
        return backtrack(head, null);
    }

    private static ListNode backtrack(ListNode head, ListNode pre) {
        if (head == null) return null;
        if (pre != null && head.val == pre.val || head.next != null && head.val == head.next.val) {
            return backtrack(head.next, head);
        } else {
            head.next = backtrack(head.next, head);
            return head;
        }
    }
}