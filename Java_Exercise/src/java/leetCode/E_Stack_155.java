package leetCode;

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

### Condition

### Essential problem

### Corner case

*/


import java.util.ArrayDeque;
import java.util.Deque;

public class E_Stack_155 {
    public static void main(String... args) {
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
    private int min = Integer.MAX_VALUE;
    private Deque<Integer> deque = new ArrayDeque<>();

    public void push(int x) {
        if (x < this.min) {
            this.min = x;
        }

        this.deque.push(x);
    }

    public void pop() {
        this.deque.pop();

        this.min = Integer.MAX_VALUE;

        if (this.deque.isEmpty()) {
            return;
        }

        for (Integer element : this.deque) {
            if (element < this.min) {
                this.min = element;
            }
        }
    }

    public int top() {
        return this.deque.peek();
    }

    public int getMin() {
        return this.min;
    }
}

