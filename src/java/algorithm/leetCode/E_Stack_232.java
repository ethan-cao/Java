package algorithm.leetCode;

/*
Implement Queue using Stacks
Implement the following operations of a queue using stacks, You must use only standard operations of a stack

### Example
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false

*/

import java.util.*;

public class E_Stack_232 {
}

/*
    Why 2 stack to implement 1 queue?

    It is to separate read & write of a queue in multi-processing. 
    one for read, and another is for write. They only interfere each other when the former one is full or latter is empty.

    When there's only one thread doing the read/write operation to the stack, there will always 1 stack empty. 
    However, in a multi-thread application, if there is only 1 queue, for thread-safety, either read or write will lock the whole queue. 
    In the two stack implementation, as long as the second stack is not empty, push operation will not lock the stack for pop.
 */

 // overall amortized cost for each operation is O(1)
class MyQueue {
    private Deque<Integer> input = new ArrayDeque<>(); // for write, push()
    private Deque<Integer> output = new ArrayDeque<>(); // for read(), peek()/pop()

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return this.input.isEmpty() && this.output.isEmpty();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        this.input.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        this.peek();  // reserve the stack
        return this.output.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        // move elements from input stack to output stack when needed
        if (this.output.isEmpty()) {
            while (!this.input.isEmpty()) {
                this.output.push(this.input.pop());
            }
        }

        return this.output.peek();
    }
}