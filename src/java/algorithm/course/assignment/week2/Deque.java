package algorithm.course.assignment.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

//  a generalization of a stack(FILO) and a queue(FIFO)
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        private final Item item;
        private Node next;
        private Node previous;

        Node(Item item) {
            this.item = item;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node firstNode = new Node(item);
        Node oldFirst = this.first;

        firstNode.next = oldFirst;

        this.first = firstNode;

        if (this.isEmpty()) {
            this.last = firstNode;
        } else {
            oldFirst.previous = firstNode;
        }

        this.size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node lastNode = new Node(item);

        if (this.isEmpty()) {
            this.first = lastNode;
        } else {
            lastNode.previous = this.last;
            this.last.next = lastNode;
        }

        this.last = lastNode;
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Node firstNode = this.first;

        this.first = firstNode.next;
        firstNode.next = null;

        this.size--;

        if (this.isEmpty()) {
            this.last = null;
        } else {
            this.first.previous = null;
        }

        return firstNode.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Node lastNode = this.last;

        this.size--;

        if (this.isEmpty()) {
            this.last = this.first = null;
        } else {
            this.last = this.last.previous;
            this.last.next = null;
        }

        return lastNode.item;
    }

    private class It implements Iterator<Item> {
        private Node current = Deque.this.first;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public Item next() {
            if (this.hasNext()) {
                Item item = this.current.item;
                this.current = this.current.next;
                return item;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new It();
    }
}
