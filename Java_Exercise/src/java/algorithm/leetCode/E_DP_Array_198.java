package algorithm.leetCode;

/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you
from robbing each of them is that adjacent houses have security system connected and
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.

### Input
int[]

### Output
int

### Example
[1,2,3,1] -> 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

[2,7,9,3,1] -> 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

[1, 3, 20, 5, 10, 10, 6] -> 37
[114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240]
-> 4173

### Condition

### Essential problem
max value amount elements that are not adjacent

max(n) : max value get rob from values[0] to values[n]

if rob values[n]      : max(n) = values[n] + max(n-2)
if not rob values[n]  : max(n) = max(n-1)

max(0) = values[0]
max(1) = Math.max(values[0], values[1])

### Corner case
values.length == 0

 */

public class E_DP_Array_198 {

    public static void main(String[] args) {
//        int[] values = {1, 2, 3, 1}; // 4
//        int[] values = {1, 3, 20, 5, 10, 10, 6}; // 37
//        int[] values ={2,7,9,3,1}; // 12
        int[] values = {114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240};

        int solution = rob1(values);
//
        System.out.println(solution);
    }

    // Recursion is slow....  rob2 is much faster
    public static int rob1(int[] values) {
        return max(values, values.length - 1);
    }

    private static int max(int[] values, int n) {
        if (0 == n) {
            return values[0];
        }

        if (1 == n) {
            return Math.max(values[0], values[1]);
        }

        int maxIfRob = values[n] + max(values, n - 2);
        int maxIfNotRob = max(values, n - 1);

        return Math.max(maxIfRob, maxIfNotRob);
    }

    public static int rob2(int[] values) {
        if (values.length < 2){
            return values[0];
        }

        int[] maxValues = new int[values.length];

        maxValues[0] = values[0];
        maxValues[1] = Math.max(values[0], values[1]);

        for (int i = 2; i < values.length; ++i){
            int maxValueIfRob = values[i] + maxValues[i-2];
            int maxValueIfNotRob = maxValues[i-1];
            maxValues[i] = Math.max(maxValueIfRob, maxValueIfNotRob);
        }

        return maxValues[values.length-1];
    }
}
