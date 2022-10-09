package algorithm.leetCode;

/*
Remove all elements from a linked list of integers that have value val.

### Example
1->2->3, val=4 ---> 1->2->3
1->2->3, val=1 ---> 2->3
1->2->3, val=2 ---> 1->3
1->2->3, val=3 ---> 1->2
1->2->6->3->4->5->6, val = 6  --->  1->2->3->4->5

*/

public class E_Recursion_LinkedList_203 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;

        ListNode node = removeElements1(node1, 1);

        while (node != null) {
            System.out.println("value : " + node.val);
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

    // 1ms
    public static ListNode removeElements(ListNode head, int val) {
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;

        ListNode node = virtualHead;

        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        return virtualHead.next;
    }

    /*
    It basically goes down to the last null node, and rebuilds the linked list,
    by adding only the nodes which are not equal to val to this null,
    so it goes from
        null ;
        [5->null]       //when head is 6 it doesnt add anything to head
        [4-->5-->null]
        [3-->4-->5-->-->null]
        [2-->3-->4-->5-->null]
        [1-->2-->3-->4-->5-->null]
    */
    public static ListNode removeElements1(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElements1(head.next, val);

        return head.val == val ? head.next : head;
    }


}
