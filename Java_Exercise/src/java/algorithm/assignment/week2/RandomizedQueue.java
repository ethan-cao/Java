package algorithm.assignment.week2;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    public static void main(String[] args) {
        // Unit test
    }

    static int INITIAL_SIZE = 16;

    private int size = 0;
    private Object[] array;

    RandomizedQueue() {
        this.array = new Object[INITIAL_SIZE];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private void resize(int capacity) {
        Object[] newArray = new Object[capacity];
        this.size = 0;

        for (int i = 0; i < this.array.length; ++i) {
            Object obj = this.array[i];

            if (obj != null) {
                newArray[this.size] = obj;
                this.size++;
            }
        }

        this.array = newArray;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (this.array.length == this.size) {
            this.resize(this.array.length * 2);
        }

        for (int i = 0; i < this.array.length; ++i) {
            if (this.array[i] == null) {
                this.array[i] = item;
                this.size++;
                break;
            }
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (this.array.length / 4 == this.size) {
            this.resize(this.array.length / 2);
        }

        Item item = this.sample();

        for (int i = 0; i < this.array.length; ++i) {
            if (this.array[i] == item) {
                this.array[i] = null;
                this.size--;
            }
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = null;
//        int index = (int) (StdRandom.uniform() * this.array.length);
        int index = StdRandom.uniform(0, this.array.length);
        while (this.array[index] == null) {
//            index = (int) (StdRandom.uniform() * this.array.length);
            index = StdRandom.uniform(0, this.array.length);
        }

        return item;
    }

    private class It implements Iterator<Item> {
        private int size;
        private Object[] snapshot = RandomizedQueue.this.array;

        It () {
            Object[] tempArray = new Object[snapshot.length];
            this.size = 0;

            for (int i = 0; i < this.snapshot.length; ++i){
                Object obj = this.snapshot[i];

                if (obj != null){
                    tempArray[this.size] = obj;
                    this.size++;
                }
            }

            this.snapshot = tempArray;

            StdRandom.shuffle(this.snapshot);
        }

        @Override
        public boolean hasNext() {
            return this.size == 0;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            return (Item) this.snapshot[this.size--];
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
