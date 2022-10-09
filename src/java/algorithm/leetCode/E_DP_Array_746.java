package algorithm.leetCode;

/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb 1 or 2 steps. You need to find minimum cost to reach the top of the floor
(reach n-th floor : go beyond n-th element) !!!
(top of the floor : go beyond the last element)  !!!
and you can either start from the step with index 0 or 1.

cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

2 < cost[].length < 1000
0 < cost[i] < 999

[1, 100, 1, 1, 1, 100, 1, 1, 100, 1] -> 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

[10, 15, 20] -> 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

*/

public class E_DP_Array_746 {
    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs2(new int[]{10, 15, 20}));
    }

    // 1ms
    private static int minCostClimbingStairs1(int[] costs) {
        final int L = costs.length;
        
        // minCosts[i]: the cost to be stair[i]
        int[] minCosts = new int[L + 1];

        // BASE
        minCosts[0] = 0;  // start from the step with index 0
        minCosts[1] = 0;  // start from the step with index 1

        // TRANSFORM
        for (int i = 2; i <= L; ++i) {
            int climbOneCost = minCosts[i - 1] + costs[i - 1];
            int climbTwoCost = minCosts[i - 2] + costs[i - 2];

            minCosts[i] = Math.min(climbOneCost, climbTwoCost);
        }

        return minCosts[L];
    }

    // condensed space
    // 1ms
    private static int minCostClimbingStairs2(int[] costs) {
        int L = costs.length;

        int cost1 = 0;
        int cost2 = 0;
        int cost3 = 0;

        for (int i = 2; i <= L; ++i) {
            int climbOneCost = cost2 + costs[i - 1];
            int climbTwoCost = cost3 + costs[i - 2];

            cost1 = Math.min(climbOneCost, climbTwoCost);
            cost3 = cost2;
            cost2 = cost1;
        }

        return cost2;
    }
}
