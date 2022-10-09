package algorithm.leetCode;

/*
Write a class StockSpanner which collects daily price quotes for some stock,
and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days
(starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

e.g, the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85]
then the stock spans would be                       [1,   1,  1,  2,  1,  4,  6]

Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.

### Example
Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
Output: [null,1,1,1,2,1,4,6]
*/

import java.util.*;

public class M_Stack_Array_901 {
    public static void main(String... args) {
        StockSpanner s = new StockSpanner();
        System.out.println(s.next(100));   // 1
        System.out.println(s.next(80));    // 1
        System.out.println(s.next(60));    // 1
        System.out.println(s.next(70));    // 2
        System.out.println(s.next(60));    // 1
        System.out.println(s.next(75));    // 4
        System.out.println(s.next(85));    // 6
    }
}

class StockSpanner {

    public StockSpanner() {
    }

    private Deque<Integer> prices = new ArrayDeque<>();
    private Deque<Integer> spans = new ArrayDeque<>(); // cache span

    // Monotonic increasing stack, 2 stack, 23ms
    // Time:O(n)
    public int next(int price) {
        int span = 1;

        while (!prices.isEmpty() && !spans.isEmpty() && prices.peekFirst() <= price) {
            prices.pop();
            span += spans.pop();
        }

        prices.push(price);
        spans.push(span);

        return span;
    }


    // Monotonic increasing stack, 1 stack, 21ms
    Stack<int[]> stack = new Stack<>();

    public int next1(int price) {
        int span = 1;

        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }

        stack.push(new int[]{price, span});
        return span;
    }
}
