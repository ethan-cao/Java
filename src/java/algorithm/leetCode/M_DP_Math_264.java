package algorithm.leetCode;

/*
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

1 is typically treated as an ugly number.
n does not exceed 1690.

### Example
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

*/


import java.util.PriorityQueue;
import java.util.TreeSet;

public class M_DP_Math_264 {

    public static void main(String... args) {
        System.out.println(nthUglyNumber(1)); // 1
        System.out.println(nthUglyNumber(2)); // 2
        System.out.println(nthUglyNumber(3)); // 3
        System.out.println(nthUglyNumber(10)); // 12
    }

    /*
    the first 3 ugly numbers 1, 2, 3. What would be the next number?
    Given the definition, the next number has to be the the smallest number among 2*(1,2,3), 3*(1,2,3), 5*(1,2,3).
    Obviously, the smallest number is 2 * 1 = 2. But wait, 2 is already in there. So precisely speaking,
    the next number has to be the the smallest number among all the existing numbers multiplied by 2, 3,5 that isn't in the list already.

    First, we assume there is only one number in the list, which is 1.
    The next number is Min(2 * 1, 3 * 1, 5 * 1)=2 and it is not in the list.
    Because we have already considered 2*1 (we move the pointer to its next position, which is 2),
    now we only need to consider 2 * 2, 3 * 1, 5 * 1 in the next iteration.
    So we don't have to worry about finding a number that is already in the list.
    */
    public static int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;

        for (int i = 1; i < n; ++i) {
            uglyNumbers[i] = Math.min(uglyNumbers[idx2] * 2, Math.min(uglyNumbers[idx3] * 3, uglyNumbers[idx5] * 5));

            // Move according index to avoid getting existing number
            if (uglyNumbers[i] == uglyNumbers[idx2] * 2) idx2++;
            if (uglyNumbers[i] == uglyNumbers[idx3] * 3) idx3++;
            if (uglyNumbers[i] == uglyNumbers[idx5] * 5) idx5++;
        }

        return uglyNumbers[n - 1];
    }

    public int nthUglyNumber1(int n) {
        if (n == 1) {
            return 1;
        }

        // array-based binary heap, keeping item sorted while inserting O(logN)
        // better than sorted array O(N)
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add(1l);

        for (long i = 1; i < n; i++) {
            long tmp = q.poll();
            while (!q.isEmpty() && q.peek() == tmp) tmp = q.poll();

            q.add(tmp * 2);
            q.add(tmp * 3);
            q.add(tmp * 5);
        }

        return q.poll().intValue();
    }

    public int nthUglyNumber2(int n) {
        TreeSet<Long> ans = new TreeSet<>();
        ans.add(1L);

        for (int i = 0; i < n - 1; ++i) {
            long first = ans.pollFirst();
            ans.add(first * 2);
            ans.add(first * 3);
            ans.add(first * 5);
        }

        return ans.first().intValue();
    }
}