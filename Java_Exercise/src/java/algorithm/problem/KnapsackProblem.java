package algorithm.problem;

import java.util.ArrayList;
import java.util.List;

// https://en.wikipedia.org/wiki/Knapsack_problem
public class KnapsackProblem {

    static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static final int capacity = 23;

    private static List<Item> items = new ArrayList<>();

    static {
        items.add(new Item(1, 5));
        items.add(new Item(2, 3));
        items.add(new Item(4, 5));
        items.add(new Item(2, 3));
        items.add(new Item(5, 2));
    }

    public static void main(String[] args) {
        // 16 when capacity is 10
        System.out.println(KnapsackProblem01.getSolution(0, items, capacity));
        System.out.println(KnapsackProblem01.getSolution1(items, capacity));
        System.out.println(KnapsackProblem01.getSolution2(items, capacity));
        System.out.println(KnapsackProblem01.getSolution3(items, capacity));


        System.out.println(UnboundedKnapsackProblem.getSolution(items, capacity));

    }


    // 0-1 knapsack problem: each item can be picked at most once
    // find max value, given picked items' weight does not exceed capacity
    // https://youtu.be/xOlhR_2QCXY
    static class KnapsackProblem01 {

        // DP recursive, DFS, Top-down, O(2^n)
        // return optimal total value
        private static int getSolution(int idx, List<Item> items, int capacity) {
            if (idx >= items.size() || capacity <= 0) {
                return 0;
            }

            Item item = items.get(idx);

            if (item.weight > capacity) {
                return getSolution(idx + 1, items, capacity);
            } else {
                int valueWithoutItem = getSolution(idx + 1, items, capacity);
                int valueWithItem = item.value + getSolution(idx + 1, items, capacity - item.weight);

                return Math.max(valueWithItem, valueWithoutItem);
            }
        }

        // DP recursive with memoization, DFS, Top-down
        // return optimal total value
        private static int getSolution1(List<Item> items, int capacity) {
            int[][] valueCache = new int[items.size() + 1][capacity + 1];

            // this leads to O(n^2), not recommended approach, just to illuminate memoization
            for (int i = 0; i <= items.size(); i++) {
                for (int j = 0; j <= capacity; j++) {
                    valueCache[i][j] = -1;
                }
            }

            return getSolution1(0, items, capacity, valueCache);
        }

        // O (n * C),  n : number of items, C : knapsack capacity
        private static int getSolution1(int idx, List<Item> items, int capacity, int[][] valueCache) {
            if (idx >= items.size() || capacity <= 0) {
                return 0;
            }

            if (valueCache[idx][capacity] != -1) {
                return valueCache[idx][capacity];
            }

            Item item = items.get(idx);
            int value;

            if (item.weight > capacity) {
                value = getSolution1(idx + 1, items, capacity, valueCache);
            } else {
                int valueWithoutItem = getSolution1(idx + 1, items, capacity, valueCache);
                int valueWithItem = item.value + getSolution1(idx + 1, items, capacity - item.weight, valueCache);

                value = Math.max(valueWithoutItem, valueWithItem);
            }

            // update cache
            valueCache[idx][capacity] = value;

            return value;
        }

        // DP iterative, Bottom-up,  O( n * C ) , n : number of items, C : knapsack capacity
        // return optimal total value
        private static int getSolution2(List<Item> items, int capacity) {
            // values[i][j] : maximal value with first i items and capacity limit j
            // Then final result we need is value[n][C]  https://youtu.be/nLmhmB6NzcM
            int[][] values = new int[items.size() + 1][capacity + 1];

            for (int i = 1; i <= items.size(); ++i) {
                for (int j = 1; j <= capacity; ++j) {
                    Item item = items.get(i - 1);

                    if (item.weight > j) {
                        values[i][j] = values[i - 1][j];
                    } else {
                        values[i][j] = Math.max(values[i - 1][j], values[i - 1][j - item.weight] + item.value);
                    }
                }
            }

            return values[items.size()][capacity];
        }

        // DP iterative with 1d array
        // By observation, draw table and observe from  DP iterative approach
        // if (item.weight > j) {
        //  values[i][j] = values[i - 1][j];
        // } else {
        //  values[i][j] = Math.max(values[i - 1][j], values[i - 1][j - item.weight] + item.value);
        // }
        // values[i][j] depends on, at most, the previous row
        // it is possible to use 1d array instead
        private static int getSolution3(List<Item> items, int capacity) {
            int[] values = new int[capacity + 1];

            for (int i = 1; i <= items.size(); ++i) {
                // each outer loop refreshes values[]
                // if iterate from 1 to capacity, values[i] is derived from the current row values[i-num]
                // but values[i] should be derived from the previous row
                for (int j = capacity; j > 0; --j) {
                    Item item = items.get(i - 1);

                    if (item.weight <= j) {
                        // values[j] is for previous items
                        values[j] = Math.max(values[j], values[j - item.weight] + item.value);
                        // values[j] has been updated current items
                    }
                }
            }

            return values[capacity];
        }
    }

    // bounded knapsack problem : each item can be picked with limit
    static class BoundedKnapsackProblem {
    }

    // bounded knapsack problem : each item can be picked without limit
    static class UnboundedKnapsackProblem {

        private static int getSolution(List<Item> items, int capacity) {
            int[][] values = new int[items.size()+1][capacity+1];

            return 1;

        }
    }

}
