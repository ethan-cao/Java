package algorithm.leetCode;

/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel
from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
otherwise return -1.

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.

### Example
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
-> 3
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.

gas  = [2,3,4]
cost = [3,4,3]
-> -1
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.

*/

public class M_Greedy_Array_134 {

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit1(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));   // 3
        System.out.println(canCompleteCircuit1(new int[]{2, 3, 4}, new int[]{3, 4, 3}));               // -1
    }

    // Time: O(N^2)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int N = gas.length;

        for (int startStation = 0; startStation < N; ++startStation) {
            int remainingGas = gas[startStation];
            int station = startStation;

            while (remainingGas >= cost[station]) {
                remainingGas -= cost[station];
                station = (station + 1) % N;

                if (station == startStation) {
                    return startStation;
                }

                remainingGas += gas[station];
            }
        }

        return -1;
    }

    // Time: O(N)
    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        int remainingGasAfterFullCircle = 0;

        for (int i = 0; i < gas.length; ++i) {
            remainingGasAfterFullCircle += gas[i] - cost[i];
        }

        // if total gas < total cost, no solution
        if (remainingGasAfterFullCircle < 0) {
            return -1;
        }

        int startStation = 0;
        int remainingGas = 0;

        // since the question guarantees 1 solution, station leads to non-empty gas is the solution
        for (int i = 0; i < gas.length; ++i) {
            remainingGas += gas[i] - cost[i];

            // if not enough gas, reset and start from the next station
            if (remainingGas < 0) {
                remainingGas = 0;
                startStation = i + 1;
            }
        }

        return startStation;
    }

    // Time: O(N)
    // compacted version canCompleteCircuit1
    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        int startStation = 0;
        int remainingGas = 0;
        int remainingGasAfterFullCircle = 0;

        // start from a station and go as far as possible until remainingGas < 0
        for (int station = 0; station < gas.length; ++station) {
            remainingGas += gas[station] - cost[station];
            remainingGasAfterFullCircle += gas[station] - cost[station];

            // if not enough gas, reset and start from the next station
            if (remainingGas < 0) {
                remainingGas = 0;
                startStation = station + 1;
            }
        }

        // if remainingGasAfterFullCircle is not negative, there must be a station
        // since the question guarantees 1 solution, startStation is the answer
        return remainingGasAfterFullCircle >= 0 ? startStation : -1;
    }

}
