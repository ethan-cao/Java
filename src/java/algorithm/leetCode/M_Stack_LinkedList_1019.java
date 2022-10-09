package algorithm.leetCode;

/*
We are given a linked list with head as the first node.
Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val
such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist,
the next larger value is 0.

Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
Note that in the example inputs (not outputs) below,
arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2,
second node value of 1, and third node value of 5.

1 <= node.val <= 10^9 for each node in the linked list.
The given list has length in the range [0, 10000].

### Example
[2,1,5] -> [5,5,0]
[2,7,4,3,5] -> [7,0,5,5,0]
[1,7,5,1,9,2,5,1] -> [7,9,9,9,0,5,0,0]

*/

import java.util.*;

public class M_Stack_LinkedList_1019 {

    public static void main(String... args) {
        ListNode head = new ListNode(2);
        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(5);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.printf(Arrays.toString(nextLargerNodes1(head)));
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static void printList(ListNode node) {
            while (node != null) {
                System.out.println(node.val);
                node = node.next;
            }
        }
    }

    // Stack, 11ms
    // Time: O(N) Space: O(N)
    public static int[] nextLargerNodes1(ListNode head) {
        int size = getSize(head);
        int[] nextLarger = new int[size];

        // tuple: [idx, node.val]
        Deque<int[]> stack = new ArrayDeque<>(); // monotonic decreasing

        int idx = 0;
        ListNode current = head;

        while (current != null) {

            while (!stack.isEmpty() && stack.peek()[1] < current.val) {
                int[] node = stack.pop();
                nextLarger[node[0]] = current.val;
            }

            stack.push(new int[] { idx, current.val });

            idx++;
            current = current.next;
        }

        return nextLarger;
    }

    private static int getSize(ListNode head) {
        int size = 0;

        ListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    // use array to mimic stack
    public static int[] nextLargerNodes2(ListNode head) {
        List<Integer> nodes = new ArrayList<>();
        for (ListNode currentNode = head; currentNode != null; currentNode = currentNode.next) {
            nodes.add(currentNode.val);
        }

        int[] solution = new int[nodes.size()];
        int[] stack = new int[nodes.size()];
        int top = -1;

        for (int i = 0; i < nodes.size(); ++i) {

            while (top != -1 && nodes.get(i) > nodes.get(stack[top])) {
                solution[stack[top--]] = nodes.get(i);
            }

            stack[++top] = i;
        }

        return solution;
    }

    // Time: O(N^2), Space: O(N)
    public static int[] nextLargerNodes3(ListNode head) {
        List<Integer> answer = new ArrayList<>();

        ListNode currentNode = head;

        while (currentNode != null) {

            ListNode nextNode = currentNode.next;
            boolean hasLargerNode = false;

            while (nextNode != null) {
                if (nextNode.val > currentNode.val) {
                    hasLargerNode = true;
                    answer.add(nextNode.val);
                    break;
                }
                nextNode = nextNode.next;
            }

            if (!hasLargerNode) {
                answer.add(0);
            }

            currentNode = currentNode.next;
        }

        int[] solution = new int[answer.size()];
        for (int i = 0; i < solution.length; ++i) {
            solution[i] = answer.get(i);
        }

        return solution;

        // even slower
        // return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}