package algorithm.leetCode;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

### Example
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

*/

public class E_Stack_155 {
    public static void main(String... args) {
//        MinStack minStack = new MinStack();
        MinStack minStack = new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }
}

class MinStack {
    private class Node {
        int val;
        int min;
        Node next;
        
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
    
    private Node head = null;
    
    public void push(int x) {
        if(this.head == null) {
            this.head = new Node(x, x, null);
        } else {
            int min = Math.min(this.head.min, x);
            this.head = new Node(x, min, this.head);
        }
    }

    public int top() {
        return head.val;
    }
        
    public void pop() {
        head = head.next;
    }

    public int getMin() {
        return head.min;
    }
}

