package leetCode;

/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb 1 or 2 steps. You need to find minimum cost to reach the top of the floor
(top of the floor : the next place after the last element in cost[])
and you can either start from the step with index 0 or 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].
-------------------------
#Input
int[] cost

#Output
int cost

#Example
[10, 15, 20] -> 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

#Condition
2 < cost[].length < 1000
0 < cost[i] < 999

#Essential problem  / Sub problem
DP
subProblem

#Solution (algorithm + data structure)

#Corner case

*/

public class DP_Array_746 {
    public static void main(String[] args) {
//        int[] costs = {10, 15, 20};
//        int[] costs = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int[] costs = {0, 1, 2, 2};

        int minCost = minCostClimbingStairs1(costs);

        System.out.println(minCost);
    }

    public static int minCostClimbingStairs1(int[] costs) {
        int minCost = Integer.MAX_VALUE;
        int L = costs.length;

        for (int startIndex = 0; startIndex < 2; ++startIndex) {
            int cost = 0;
            int currentIndex = startIndex;

            while (currentIndex < L) {
                cost += costs[currentIndex];

                if (currentIndex + 1 >= L || currentIndex + 2 >= L ) {
                    break;
                }

                int stepToMove = costs[currentIndex + 2] > costs[currentIndex + 1] ? 1 : 2;

                currentIndex += stepToMove;
            }

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }
}
