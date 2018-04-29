package algorithm.colllection;

import java.lang.reflect.Array;
import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {
        final String[] test = "to be or not to - be - - that - - - is".split(" ");

//        StackAPI<String> stack = new StackByLinkedList<>();
        StackAPI<String> stack = new StackByArray<>(100, String.class);

        for (String s : test){
            if ( s.equals("-") ){
                System.out.print(stack.pop() + " ");
                // expect : to be not that or be
            } else {
                stack.push("" + s);
            }
        }

        System.out.println("");
        System.out.println("size : " +stack.size());  // expect 2

    }
}

// Generic interface
interface StackAPI<T> {
    void push(T t);
    T pop();
    boolean isEmpty();
    int size();
}

// without <T> following StackAPI, you get Object instead of T in your interface's methods.
class StackByLinkedList<T> implements StackAPI<T> {
    private Node first; // pointer to the first node

    private class Node {
        T element;
        Node next;

        Node(T element) {
            this.element = element;
        }
    }

    @Override
    public void push(T element) {
        Node previousFirst = this.first;

        this.first = new Node(element);
        this.first.next = previousFirst;
    }

    @Override
    public T pop() {
        Node previousFirst = this.first;

        this.first = this.first.next;

        return previousFirst.element;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public int size() {
        if (first == null) {
            return 0;
        }

        int size = 1;
        Node next = first.next;
        while (next != null) {
            size++;
            next = next.next;
        }
        return size;
    }
}

class StackByArray<T> implements StackAPI<T> {
    private T[] stack;
    private int n = 0;

    @SuppressWarnings("unchecked")
    StackByArray(int n, final Class<T> tClass) {
        //  create a generic array
        this.stack = (T[]) Array.newInstance(tClass, n);
    }

    @Override
    public void push(T o) {
        this.stack[n++] = o;
    }

    @Override
    public T pop() {
        T topElement = this.stack[--n];
        this.stack[n] = null;
        return topElement;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }
}

class StackByResizingArray<T> implements StackAPI<T>{

    @Override
    public void push(T t) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}