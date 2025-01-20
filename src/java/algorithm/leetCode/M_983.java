package algorithm.leetCode;

/*
You have planned some train traveling one year in advance.
The days of the year in which you will travel are given as an integer array days.
Return the minimum number of dollars you need to travel every day in the given list of days.

Train tickets are sold in three different ways:
    a 1-day pass is sold for costs[0] dollars,
    a 7-day pass is sold for costs[1] dollars, and
    a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

Each day is an integer from 1 to 365.
1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000

### Example
days = [1,4,6,7,8,20], costs = [2,7,15] -> 11
days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15] -> 17

*/

public class M_983 {
    public static void main(String[] args) {
        System.out.println(mincostTickets(new int[] { 1, 4, 6, 7, 8, 20 }, new int[] { 7, 2, 15 }));
    }

    // DP, recursive, memo
    // 0ms
    public static int mincostTickets(int[] days, int[] costs) {
        int firstDay = days[0];
        int lastDay = days[days.length - 1];

        boolean[] isTravelDay = new boolean[lastDay - firstDay + 1];
        for (int day : days) {
            isTravelDay[day - firstDay] = true;
        }

        int idxOfLastDay = lastDay - firstDay;

        Integer[] memo = new Integer[lastDay - firstDay + 1];

        return count(days, idxOfLastDay, costs, isTravelDay, memo);
    }

    private static int count(int[] days, int idx, int[] costs, boolean[] isTravelDay, Integer[] memo) {
        if (idx == 0) {
            // !!! use the minimal one
            return Math.min(costs[0], Math.min(costs[1], costs[2]));
        }

        if (memo[idx] != null) {
            return memo[idx];
        }

        memo[idx] = Integer.MAX_VALUE;

        if (isTravelDay[idx]) {
            int costWithDayTicket = costs[0] + (idx >= 1 ? count(days, idx - 1, costs, isTravelDay, memo) : 0);
            int costWithWeekTicket = costs[1] + (idx >= 7 ? count(days, idx - 7, costs, isTravelDay, memo) : 0);
            int costWithMonthTicket = costs[2] + (idx >= 30 ? count(days, idx - 30, costs, isTravelDay, memo) : 0);

            memo[idx] = Math.min(costWithDayTicket, Math.min(costWithWeekTicket, costWithMonthTicket));
        } else {
            memo[idx] = count(days, idx - 1, costs, isTravelDay, memo);
        }

        return memo[idx];
    }

    // DP, iterative
    // 1ms
    public int mincostTickets1(int[] days, int[] costs) {
        int dayTicketCost = costs[0];
        int weekTicketCost = costs[1];
        int monthTicketCost = costs[2];

        int lastTravelDay = days[days.length - 1];
        int[] minCosts = new int[lastTravelDay + 1];
        boolean[] isTravelDay = new boolean[lastTravelDay + 1];
        for (int day : days) {
            isTravelDay[day] = true;
        }

        for (int i = 1; i <= lastTravelDay; ++i) {
            if (isTravelDay[i]) {
                int costWithDayTicket = dayTicketCost + minCosts[i - 1];
                int costWithWeekTicket = i - 7 >= 0 ? weekTicketCost + minCosts[i - 7] : weekTicketCost;
                int costWithMonthTicket = i - 30 >= 0 ? monthTicketCost + minCosts[i - 30] : monthTicketCost;

                minCosts[i] = Math.min(costWithDayTicket, Math.min(costWithWeekTicket, costWithMonthTicket));
            } else {
                minCosts[i] = minCosts[i - 1];
            }

        }

        return minCosts[lastTravelDay];
    }

    // DP, iterative, condensed space
    // 1ms
    public int mincostTickets2(int[] days, int[] costs) {
        int firstDay = days[0];
        int lastDay = days[days.length - 1];
        int[] minCosts = new int[lastDay - firstDay + 1];

        // alternatively, use Set
        boolean[] isTravelDay = new boolean[lastDay - firstDay + 1];
        for (int day : days) {
            isTravelDay[day - firstDay] = true;
        }

        for (int day = firstDay; day <= lastDay; ++day) {
            int idxOfDay = day - firstDay;

            if (isTravelDay[idxOfDay]) {
                int costWithDayTicket = costs[0] + (idxOfDay >= 1 ? minCosts[idxOfDay - 1] : 0);
                int costWithWeekTicket = costs[1] + (idxOfDay >= 7 ? minCosts[idxOfDay - 7] : 0);
                int costWithMonthTicket = costs[2] + (idxOfDay >= 30 ? minCosts[idxOfDay - 30] : 0);

                minCosts[idxOfDay] = Math.min(costWithDayTicket, Math.min(costWithWeekTicket, costWithMonthTicket));
            } else {
                minCosts[idxOfDay] = (idxOfDay >= 1 ? minCosts[idxOfDay - 1] : 0);
            }

        }

        return minCosts[lastDay - firstDay];
    }

    // BFS
    // 1ms
    public int mincostTickets0(int[] days, int[] costs) {
        int[] memo = new int[days.length];
        return getCost(days, costs, 0, memo);
    }

    private int getCost(int[] days, int[] costs, int idx, int[] memo) {
        if (idx == days.length) {
            return 0;
        }

        if (memo[idx] != 0) {
            return memo[idx];
        }

        int costWithDayTicket = costs[0] + getCost(days, costs, idx + 1, memo);

        int nextIdxInAWeek = idx + 1;
        while (nextIdxInAWeek < days.length && days[nextIdxInAWeek] <= days[idx] + 6) {
            nextIdxInAWeek++;
        }
        int costWithWeekTicket = Integer.MAX_VALUE;
        if (nextIdxInAWeek <= days.length) { // !!! use = to reach idx == days.length
            costWithWeekTicket = costs[1] + getCost(days, costs, nextIdxInAWeek, memo);
        }

        int nextIdxInAMonth = idx + 1;
        while (nextIdxInAMonth < days.length && days[nextIdxInAMonth] <= days[idx] + 29) {
            nextIdxInAMonth++;
        }
        int costWithMonthTicket = Integer.MAX_VALUE;
        if (nextIdxInAMonth <= days.length) { // !!! use = to reach idx == days.length
            costWithMonthTicket = costs[2] + getCost(days, costs, nextIdxInAMonth, memo);
        }

        memo[idx] = Math.min(costWithDayTicket, Math.min(costWithWeekTicket, costWithMonthTicket));
        return memo[idx];
    }
}
