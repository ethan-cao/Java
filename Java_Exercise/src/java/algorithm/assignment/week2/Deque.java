package algorithm.assignment.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

//  a generalization of a stack(FILO) and a queue(FIFO)
public class Deque<Item> implements Iterable<Item> {
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        deque.addLast(1);
        deque.removeFirst();
        deque.addFirst(2);
        deque.addFirst(1);

        for (Integer i : deque) {
            System.out.print(i + " ");
        }
        System.out.print(" size : " + deque.size());

    }

    private class Node {
        private Item item;
        private Node next;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node first;
    private Node last;
    private int size = 0;

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
        firstNode.next = this.first;

        this.first = firstNode;

        if (this.isEmpty()) {
            this.last = firstNode;
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
        } else{
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
            Node node = this.first;
            while (node.next != lastNode) {
                node = node.next;
            }
            this.last = node;
            node.next = null;
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

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new It();
    }
}
