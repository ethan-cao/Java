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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class E_Stack_232 {
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
class MyQueue1 {
    private Stack<Integer> input = new Stack();
    private Stack<Integer> output = new Stack();

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        input.push(x);
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
        if (this.output.empty()) {
            while (!this.input.empty()) {
                this.output.push(this.input.pop());
            }
        }

        return this.output.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return this.input.empty() && this.output.empty();
    }

}

class MyQueue {
    private Deque list = new ArrayDeque();

    public MyQueue() {
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        this.list.offer(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        return (int) this.list.poll();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return (int) this.list.getFirst();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return this.list.isEmpty();
    }
}

