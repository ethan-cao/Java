package collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeTest {

    public static void main(String... args){
        // use ArrayDeque for Stack
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //   1 - 2 - 3 - 4
        //  tail         head

        System.out.println(stack.peekFirst()); // 4
        System.out.println(stack.peekLast()); // 1


        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        //   1 - 2 - 3 - 4
        //  head        tail

        System.out.println(queue.peekFirst()); // 1
        System.out.println(queue.peekLast()); // 4
    }


}
