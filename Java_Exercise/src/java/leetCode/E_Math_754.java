package leetCode;

/*
You are standing at position 0 on an infinite number line.
There is a goal at position target.
On each move, you can either go left or right.
During the n-th move (starting from 1), you take n steps.
Return the minimum number of steps required to reach the destination.

target will be a non-zero integer in the range [-10^9, 10^9].

### Example
3 -> 3
Explanation: On the first move we step from 0 to 1.
On the second step we step from 1 to 3.

2 --> 3
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.

### Condition


### Essential problem

### Corner case

*/

public class E_Math_754 {
    public static void main(String... args) {
//        System.out.println(reachNumber(1));  // 1
//        System.out.println(reachNumber(2));  // 3
//        System.out.println(reachNumber(3));  // 2
//        System.out.println(reachNumber(4));  // 3
//        System.out.println(reachNumber(5));  // 5
//        System.out.println(reachNumber(6));  // 3

        System.out.println(reachNumber(-1000000000));  // 44723

    }

    public static int reachNumber(int target) {
        long step = 0;

        target = Math.abs(target); // due to symmetry

        // if use 8, 8 * target is int, it could overflow
        // use 8.0, so 8 * target is float, larger range
        long lowerLimit = (long) Math.ceil((Math.sqrt(1 + 8.0 * target) - 1) / 2); // quadratic equation
        long sum = (1 + lowerLimit) * lowerLimit / 2;
        long delta = sum - target;  // sum>=target

        if (delta == 0) {
            step = lowerLimit;
        } else if (delta % 2 == 0) {
            // if delta is even, flip one number x in [1,n] to be -x.
            // since i-th move, if we switch right move to left move, summation will be 2*i less
            step = lowerLimit;
        } else if ((delta + lowerLimit + 1) % 2 == 0) {
            // if delta is odd, and n+1 is odd, we can first add n+1, then delta is even. Next flip an x to be -x
            step = lowerLimit + 1;
        } else {
            // If delta is odd and n+1 is even, we add n+1 then subtract n+2, delta becomes even, then flip an x
            step = lowerLimit + 2;
        }

        return (int) step;
    }
}
