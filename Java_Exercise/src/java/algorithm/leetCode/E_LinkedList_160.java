package algorithm.leetCode;

/*
Write a program to find the node at which the intersection of two singly linked lists begins.


### Example

a1->a2->c1->c2->c3
b1->b2->c1->c2->c3
---> Reference of the node with value = C1


   4->1->8->4->5
5->0->1->8->4->5
---> Reference of the node with value = 8


0->9->1->2->4
      3->2->4
---> Reference of the node with value = 2

2->6->4
1->5
---> null


### Condition
If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.


### Essential problem

### Corner case

*/

public class E_LinkedList_160 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(8);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(0);
        ListNode node8 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node6.next = node7;
        node7.next = node8;
        node8.next = node3;


        ListNode node = getIntersectionNode0(node1, node6);

//        while (node != null) {
//            System.out.println(node.val);
//            node = node.next;
//        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // works but very slow
    public static ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode nodeA = headA;
        while (nodeA != null) {

            ListNode nodeB = headB;
            while (nodeB != null) {

                if (nodeA == nodeB) {
                    return nodeA;
                }

                nodeB = nodeB.next;
            }
            nodeA = nodeA.next;
        }

        return null;
    }

    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode nodeA = headA;
        ListNode nodeB = headB;

        /*
            this results in 2 iterations
            Suppose lenght list A is a + c, lenght list B is a + c
            c is the length of intersection

            in 1st iteration, pinterA moves a + c, pointerB moves b + c,
            after switching pointer, pointerA moves b + c , pointerB moves a + c

            eventually, pointerA moves a + c + b + c, pointerB moves b + c + a + c
            since a + c + b = b + c + a, 2 pinters meets in the beginning of intersection
         */
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }

        return nodeA;
    }

    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // Get the length of the two lists.
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);

        // make 2 pointers have the same length to iterate
        if (lengthA > lengthB) {
            while (lengthA == lengthB) {
                headA = headA.next;
                lengthA--;
            }
        } else {
            while (lengthA == lengthB) {
                headB = headB.next;
                lengthB--;
            }
        }

        // iterate until find the identical node
        while (headA == headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    public static int getLength(ListNode node) {
        int length = 0;

        while (node != null) {
            length++;
            node = node.next;
        }

        return length;
    }

}
