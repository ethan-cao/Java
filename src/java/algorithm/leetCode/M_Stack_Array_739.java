package algorithm.leetCode;

/*
Given a list of daily temperatures T, return a list such that, for each day in the input,
tells you how many days you would have to wait until a warmer temperature.
If there is no future day for which this is possible, put 0 instead.

T ∈ [1, 30000]. Each t ∈ [30, 100].

### Example
    [73, 74, 75, 71, 69, 72, 76, 73]
->  [1,  1,  4,  2,  1,  1,  0,  0]

*/

import java.util.*;

public class M_Stack_Array_739 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(dailyTemperatures1(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))); // 1, 1, 4, 2, 1, 1, 0, 0
    }

    // Stack, 12ms
    // Time: O(N), Space: O(N)
    public static int[] dailyTemperatures1(int[] T) {
        int[] waitingDays = new int[T.length];

        // stack contains which day, and tempeature in these days are descreasing
        Deque<Integer> stack = new ArrayDeque<>();  // monotonic descreasing

        for (int i = 0; i < T.length; ++i) {

            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int day = stack.pop();
                waitingDays[day] = i - day;
            }

            // each item is pushed or popped at most once
            stack.push(i);
        }

        return waitingDays;
    }

    static class Pair<T> {
        private T day;
        private T temperature;

        Pair(T day, T temperature) {
            Objects.requireNonNull(day);
            Objects.requireNonNull(temperature);

            this.day = day;
            this.temperature = temperature;
        }

        @Override
        public boolean equals(Object anotherPair) {
            if (!(anotherPair instanceof Pair)) {
                return false;
            }

            if (this == anotherPair) {
                return true;
            }

            Pair aPair = (Pair) anotherPair;

            return this.temperature.equals(aPair.temperature) && this.day.equals(aPair.day);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.day, this.temperature);
        }
    }

    // Monotonic Stack with Pair
    // Time: O(N), Space: O(N)
    public static int[] dailyTemperatures(int[] T) {
        int[] waitingDays = new int[T.length];

        Deque<Pair<Integer>> stack = new ArrayDeque<>();

        for (int i = 0; i < T.length; ++i) {

            while (!stack.isEmpty() && stack.peekFirst().temperature < T[i]) {
                int day = stack.pop().day;
                waitingDays[day] = i - day;
            }

            stack.push(new Pair<Integer>(i, T[i]));
        }

        return waitingDays;
    }

}
