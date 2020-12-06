package algorithm.course.assignment.week2.queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_SIZE = 32;
    private int size = 0;
    private Object[] queue;

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.dequeue();

        System.out.println("size : " + q.size());
        System.out.println("");

        Iterator<Integer> it = q.iterator();
        while(it.hasNext() ){
            Integer i =  it.next();
            System.out.println(i);
        }

    }

    public RandomizedQueue() {
        this.queue = new Object[INITIAL_SIZE];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private void resize(int capacity) {
        if (this.size >= capacity) {
            return;
        }

        Object[] newArray = new Object[capacity];
        this.size = 0;

        for (int i = 0; i < this.queue.length; ++i) {
            Object obj = this.queue[i];

            if (obj != null) {
                newArray[this.size] = obj;
                this.size++;
            }
        }

        this.queue = newArray;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (this.queue.length == this.size) {
            this.resize(this.queue.length * 2);
        }

        this.queue[this.size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int length = this.queue.length;

        if (length > 32 && length / 4 == this.size) {
            this.resize(length / 2);
        }

        int index = StdRandom.uniform(this.size);
        Item item = (Item) this.queue[index];

        if (index == this.size - 1) {
            this.queue[index] = null;
        } else {
            this.queue[index] = this.queue[this.size - 1];
            this.queue[this.size - 1] = null;
        }

        this.size--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size);

        return (Item) this.queue[index];
    }

    private class It implements Iterator<Item> {
        private int size = 0;
        private final Object[] snapshot;

        It() {
            this.size = RandomizedQueue.this.size();
            this.snapshot = new Object[this.size];
            System.arraycopy(RandomizedQueue.this.queue, 0, this.snapshot, 0, this.size);
            StdRandom.shuffle(this.snapshot);
        }

        @Override
        public boolean hasNext() {
            return this.size != 0;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            return (Item) this.snapshot[--this.size];
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
