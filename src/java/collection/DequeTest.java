package collection;

import java.util.*;

public class DequeTest {

    public static void main(String... args){
        // use ArrayDeque as Stack (FILO)
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //   4 - 3 - 2 - 1
        // first         last
        // head          tail

        // stack.pop(); // this removes 4

        System.out.println(stack.peekFirst()); // 4   //  peek() == peekFirst()
        System.out.println(stack.peekLast()); // 1


        // use ArrayDeque as Queue (FIFO)
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);   // offer() cannot take null, throws exception
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        //   1 - 2 - 3 - 4
        //  first       last
        //  head        tail

        // queue.poll(); // this removes 1

        System.out.println(queue.peekFirst()); // 1    //  peek() == peekFirst()
        System.out.println(queue.peekLast()); // 4
    }


}
