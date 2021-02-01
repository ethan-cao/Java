package algorithm.leetCode;

/*
In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2
such that it is possible to split the entire deck into 1 or more groups of cards,
where:
    1. Each group has exactly X cards.
    2. All the cards in each group have the same integer.

1 <= deck.length <= 10000
0 <= deck[i] < 10000

### Example
[1,2,3,4,4,3,2,1] --> true
Explanation: Possible getPartitionKey [1,1],[2,2],[3,3],[4,4]

[1,1,1,2,2,2,3,3] -->  false
Explanation: No possible getPartitionKey.

[1] --> false
Explanation: No possible getPartitionKey.

[1,1] -->  true
Explanation: Possible getPartitionKey [1,1]

[1,1,2,2,2,2] --> true
Explanation: Possible getPartitionKey [1,1],[2,2],[2,2]

groupCount * elementCount = deck.length
 deck.length >= elementCount >= 2
 deck.length > groupCount >= 1

### Essential problem
GCD (Greatest Common Divisor)

*/

import java.util.HashMap;
import java.util.Map;

public class E_Math_Array_914 {
    public static void main(String... args) {
//        int[] deck = {1, 2, 3, 4, 4, 3, 2, 1};  // T
//        int[] deck = {1, 1, 1, 2, 2, 2, 3, 3};  // F
//        int[] deck = {1, 1, 2, 2, 2, 2};  // T
//        int[] deck = {1, 1};  // T
        int[] deck = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1};  // T

        System.out.println(hasGroupsSizeX1(deck));
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        int length = deck.length;
        if (length <= 1) {
            return false;
        }

        Map<Integer, Integer> counter = new HashMap<>();
        for (int card : deck) {
            counter.put(card, counter.getOrDefault(card, 0) + 1);
        }

        for (int elementCount = 2; elementCount <= length; ++elementCount) {
            if (length % elementCount != 0) {
                continue;
            }

            int groupCount = length / elementCount;
            if (groupCount < counter.size()) {
                continue;
            }

            boolean result = true;
            for (Map.Entry entry : counter.entrySet()) {
                int number = (int) entry.getValue();

                result &= number % elementCount == 0;
            }

            if (result) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasGroupsSizeX1(int[] deck) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int card : deck) {
            counter.put(card, counter.getOrDefault(card, 0) + 1);
        }

        int greatestCommonDivisor = 0;
        for (int count : counter.values()) {
            greatestCommonDivisor = getGreatestCommonDivisor(count, greatestCommonDivisor);
        }
        return greatestCommonDivisor > 1;
    }

    static int getGreatestCommonDivisor(int a, int b) {
        return b > 0 ? getGreatestCommonDivisor(b, a % b) : a;
    }
}
