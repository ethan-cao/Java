package algorithm.assignment.week2;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    public static void main(String[] args)   {
    }

    private int size = 0;

    public RandomizedQueue() {}

    public boolean isEmpty()  {
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    // add the item
    public void enqueue(Item item)           {
    }

    // remove and return a random item
    public Item dequeue()             {
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample()    {
        return null;
    }

    private class It implements Iterator<Item>{
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator()         {
        return new It();
    }
}
