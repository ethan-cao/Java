package designPattern;


import java.util.Iterator;

/**
 * It provide a way to access the elements of a collection without exposing the underlying representation
 *
 * e.g. java.util.ArrayList#iterator()
 */

class IteratorPattern {
    public static void main(String[] args) {
        algorithm.course.assignment.week2.Deque deque  = new algorithm.course.assignment.week2.Deque();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator iterator = deque.iterator();

        while(iterator.hasNext()){
            System.out.printf(iterator.next() + " ");
        }

    }
}


