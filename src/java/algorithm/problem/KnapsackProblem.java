package algorithm.problem;

import java.util.*;

/**
 * Given a set of items, each with a weight and a value, determine the number of item to include in a bag,
 * so that the total weight is less than or equal to a given limit and the total value is as large as possible
 * When capacity is 0, solution is always 0
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * <p>
 * Great example for DP
 * <p>
 * Get optimal total value using items without exceeding capacity
 */

public class KnapsackProblem {

    static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static List<Item> items = new ArrayList<>();
    static int capacity = 100;

    static {
        items.add(new Item(7, 5));
        items.add(new Item(2, 3));
        items.add(new Item(4, 5));
        items.add(new Item(2, 7));
        items.add(new Item(5, 2));
    }

    public static void main(String[] args) {
        // 15 (capacity = 10)
//        System.out.println(KnapsackProblem01.getSolution(items, capacity));
//        System.out.println(KnapsackProblem01.getSolution1(items, capacity));
//        System.out.println(KnapsackProblem01.getSolution2(items, capacity));
//        System.out.println(KnapsackProblem01.getSolution3(items, capacity));

        // 35 (capacity = 10)
        System.out.println(UnboundedKnapsackProblem.getSolution(items, capacity));
        System.out.println(UnboundedKnapsackProblem.getSolution1(items, capacity));
        System.out.println(UnboundedKnapsackProblem.getSolution2(items, capacity));
        System.out.println(UnboundedKnapsackProblem.getSolution3(items, capacity));
    }


    /**
     * 0-1 knapsack problem: each item can be picked at most once
     * https://youtu.be/xOlhR_2QCXY
     */
    static class KnapsackProblem01 {

        // DP recursive, DFS, top-down,
        // Time: O(2^n)
        public static int getSolution(List<Item> items, int capacity) {
            return getSolution(items, 0, capacity);
        }

        private static int getSolution(List<Item> items, int idx, int remainingCapacity) {
            if (idx >= items.size() || remainingCapacity <= 0) {
                return 0;
            }

            Item item = items.get(idx);

            if (item.weight > remainingCapacity) {
                return getSolution(items, idx + 1, remainingCapacity);
            } else {
                int valueWithoutItem = getSolution(items, idx + 1, remainingCapacity);
                int valueWithItem = item.value + getSolution(items, idx + 1, remainingCapacity - item.weight);

                return Math.max(valueWithItem, valueWithoutItem);
            }
        }

        // DP recursive, DFS, top-down, memoization
        private static int getSolution1(List<Item> items, int capacity) {
            int[][] memo = new int[items.size() + 1][capacity + 1];

            // initialization, this leads to O(n^2)
            for (int i = 0; i <= items.size(); i++) {
                Arrays.fill(memo[i], -1);
            }

            return getValue(items, 0, capacity, memo);
        }

        // O (n * C),  n : number of items, C : knapsack capacity
        private static int getValue(List<Item> items, int idx, int remainingCapacity, int[][] memo) {
            if (idx >= items.size() || remainingCapacity <= 0) {
                return 0;
            }

            if (memo[idx][remainingCapacity] != -1) {
                return memo[idx][remainingCapacity];
            }

            Item item = items.get(idx);
            int value;

            if (item.weight > remainingCapacity) {
                value = getValue(items, idx + 1, remainingCapacity, memo);
            } else {
                int valueWithoutItem = getValue(items, idx + 1, remainingCapacity, memo);
                int valueWithItem = item.value + getValue(items, idx + 1, remainingCapacity - item.weight, memo);

                value = Math.max(valueWithoutItem, valueWithItem);
            }

            // update memo
            memo[idx][remainingCapacity] = value;

            return value;
        }



        // DP iterative, bottom-up
        // Time O( n * C ) , n : number of items, C : knapsack capacity
        // https://youtu.be/nLmhmB6NzcM
        private static int getSolution2(List<Item> items, int capacity) {
            // values[i][j]: maximal value with first i items and capacity limit j
            // the final result is value[n][C]
            int[][] values = new int[items.size() + 1][capacity + 1];

            for (int i = 1; i <= items.size(); ++i) {
                for (int j = 1; j <= capacity; ++j) {
                    Item item = items.get(i - 1);

                    if (item.weight > j) {
                        values[i][j] = values[i - 1][j];
                    } else {
                        int valueWithItem = values[i - 1][j - item.weight] + item.value;
                        int valueWithoutItem = values[i - 1][j];

                        values[i][j] = Math.max(valueWithItem, valueWithoutItem);
                    }
                }
            }

            return values[items.size()][capacity];
        }

        // DP, iterative, bottom-up, condensed space
        // By observation, draw table and observe from DP iterative approach
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
                        int valueWithItem = values[j - item.weight] + item.value;
                        int valueWithoutItem = values[j];

                        values[j] = Math.max(valueWithItem, valueWithoutItem);

                        // values[j] has been updated current items
                    }
                }
            }

            return values[capacity];
        }
    }

    /**
     * unbounded knapsack problem : each item can be picked without limit
     */
    static class UnboundedKnapsackProblem {

        // DP, recursive, DFS, top-down
        public static int getSolution(List<Item> items, int capacity) {
            return getValue(items, 0, capacity);
        }

        private static int getValue(List<Item> items, int idx, int capacity) {
            if (capacity == 0) {
                return 0;
            }

            int value = 0;

            // we can pick only one kind of items at a time, the max value
            for (int i = idx; i < items.size(); ++i) {
                Item item = items.get(i);

                if (item.weight <= capacity) {
                    // still check the same kind of item, since there is no limit
                    int valueWithItem = item.value + getValue(items, i, capacity - item.weight);
                    value = Math.max(value, valueWithItem);
                }
            }

            return value;
        }

        // DP, recursive, DFS, top-down, memoization
        public static int getSolution1(List<Item> items, int capacity) {
            int[][] valueCache = new int[items.size() + 1][capacity + 1];

            for (int i = 0; i <= items.size(); ++i) {
                Arrays.fill(valueCache[i], -1);
            }

            return getValue(items, 1, capacity, valueCache);
        }

        private static int getValue(List<Item> items, int idx, int capacity, int[][] cache) {
            if (capacity <= 0 || idx > items.size()) {
                return 0;
            }

            if (cache[idx][capacity] != -1) {
                return cache[idx][capacity];
            }

            int value = 0;

            for (int i = idx; i <= items.size(); ++i) {
                Item item = items.get(i - 1);

                if (item.weight <= capacity) {
                    int valueWithItem = item.value + getValue(items, i, capacity - item.weight, cache);
                    value = Math.max(value, valueWithItem);
                }
            }

            cache[idx][capacity] = value;

            return value;
        }

        // DP, iterative, bottom-up
        public static int getSolution2(List<Item> items, int capacity) {
            int[][] values = new int[items.size() + 1][capacity + 1];

            for (int i = 1; i <= items.size(); ++i) {
                for (int j = 1; j <= capacity; ++j) {
                    Item item = items.get(i - 1);

                    if (item.weight > capacity) {
                        values[i][j] = values[i - 1][j];
                    } else {
                        values[i][j] = Math.max(values[i - 1][j], capacity / item.weight * item.value);
                    }
                }
            }

            return values[items.size()][capacity];
        }

        // DP, iterative, bottom-up, optimized with 1d array
        public static int getSolution3(List<Item> items, int capacity) {
            int[] values = new int[capacity + 1];

            for (int i = 1; i <= items.size(); ++i) {
                for (int j = capacity; j >= 0; --j) {
                    Item item = items.get(i - 1);

                    if (item.weight > capacity) {
                        values[j] = values[j];
                    } else {
                        values[j] = Math.max(values[j], capacity / item.weight * item.value);
                    }
                }
            }

            return values[capacity];
        }
    }

    /**
     * bounded knapsack problem : each item can be picked with limit
     */
    static class BoundedKnapsackProblem {
    }

}