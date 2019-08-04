package algorithm.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Knapsack_problem
 *
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

    private static List<Item> items = new ArrayList<>();

    static {
        items.add(new Item(7, 5));
        items.add(new Item(2, 3));
        items.add(new Item(4, 5));
        items.add(new Item(2, 3));
        items.add(new Item(5, 2));
    }

    private static final int capacity = 10;

    public static void main(String[] args) {
        // for all case, when capacity is 0, solution is always 0

        // 11 (capacity = 10)
        System.out.println(KnapsackProblem01.getSolution(0, items, capacity));
        System.out.println(KnapsackProblem01.getSolution1(items, capacity));
        System.out.println(KnapsackProblem01.getSolution2(items, capacity));
        System.out.println(KnapsackProblem01.getSolution3(items, capacity));


        // 15 (capacity = 10)
        System.out.println(UnboundedKnapsackProblem.getSolution(items, capacity));
        System.out.println(UnboundedKnapsackProblem.getSolution1(items, capacity));
        System.out.println(UnboundedKnapsackProblem.getSolution2(items, capacity));
        System.out.println(UnboundedKnapsackProblem.getSolution3(items, capacity));
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

        // DP, recursive, top-down
        public static int getSolution(List<Item> items, int capacity) {
            return getChange(items, 0, capacity);
        }

        private static int getChange(List<Item> items, int idx, int capacity) {
            if (capacity == 0) {
                return 0;
            }

            int solution = 0;

            // we can pick only 1 item, the max value
            for (int i = idx; i < items.size(); ++i) {
                Item item = items.get(i);

                if (item.weight <= capacity) {
                    int valueWithItem = item.value + getChange(items, i, capacity - item.weight);

                    solution = Math.max(valueWithItem, solution);
                }
            }

            return solution;
        }

        // DP, recursive, top-down, with cache
        public static int getSolution1(List<Item> items, int capacity) {


            return 1;
        }

        // DP, iterative, bottom-up
        public static int getSolution2(List<Item> items, int capacity) {
            int[][] values = new int[items.size() + 1][capacity + 1];

            for (int i = 1; i <= items.size(); ++i) {
                for (int j = 1; j <= capacity; ++j) {
                    Item item = items.get(i-1);

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
                    Item item = items.get(i-1);

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

}
