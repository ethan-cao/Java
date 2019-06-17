package algorithm.leetCode;

/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb 1 or 2 steps. You need to find minimum cost to reach the top of the floor
(reach n-th floor : go beyond n-th element) !!!
(top of the floor : go beyond the last element)  !!!
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

### Input
int[] cost

### Output
int cost

### Example
[10, 15, 20] -> 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

### Condition
2 < cost[].length < 1000
0 < cost[i] < 999

### Essential problem

======== minCostClimbingStairs1
(reach n-th floor : go beyond n-th element) !!!
cost[i] : the minimal cost to reach i-th floor
cost[i]  = costs[i] + min(cost[i-1], cost[i-2])

cost[0] = costs[0]
cost[1] = costs[1]

L = costs.length
reach the top floor : min(cost[L-1], cost[L-2])


### Corner case

*/

public class E_DP_Array_746 {
    public static void main(String[] args) {
        int[] costs = {10, 15, 20};
//        int[] costs = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        int[] costs = {0, 1, 2, 2};

        int minCost = minCostClimbingStairs1(costs);
        minCost = minCostClimbingStairs2(costs);

        System.out.println(minCost);
    }

    private static int minCostClimbingStairs1(int[] costs) {
        int L = costs.length;
        int[] cost = new int[L]; // minimal cost to reach i-th floor

        cost[0] = costs[0];
        cost[1] = costs[1];

        for (int i = 2 ; i < L ; ++i){
            cost[i] = costs[i] + Math.min(cost[i-1], cost[i-2]);
        }

        return Math.min(cost[L-1], cost[L-2]);
    }

    // simplified version of minCostClimbingStairs2
    private static int minCostClimbingStairs2(int[] costs) {
        for (int i = 2; i < costs.length; i++) {
            costs[i] += Math.min(costs[i-1], costs[i-2]);
        }
        return Math.min(costs[costs.length-1], costs[costs.length-2]);
    }
}
