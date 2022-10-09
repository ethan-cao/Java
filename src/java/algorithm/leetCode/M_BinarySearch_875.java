package algorithm.leetCode;

/*
Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.
The guards have gone and will come back in H hours. Koko can decide her bananas-per-hour eating speed of K.
Each hour, she chooses some pile of bananas, and eats K bananas from that pile.
If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
Return the minimum integer K such that she can eat all the bananas within H hours.

1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9

### Example
piles = [3,6,7,11], H = 8 -> 4
piles = [30,11,23,4,20], H = 5 -> 30
piles = [30,11,23,4,20], H = 6 -> 23

*/

public class M_BinarySearch_875 {

    // lower-bound Binary Search
    // Time: O(logN), 33ms
    public int minEatingSpeed(int[] piles, int H) {
        int[] bounds = getBounds(piles, H);
        int left = bounds[0];
        int right = bounds[1];

        while (left <= right) {
            int middle = left + (right - left) / 2;
            boolean canEatAll = canEatAll(piles, H, middle);

            if (canEatAll) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }

    private int[] getBounds(int[] piles, int H) {
        double sum = 0.0;  // !!!
        int upperBound = Integer.MIN_VALUE;

        for (int val : piles) {
            sum += val;
            upperBound = Math.max(upperBound, val);
        }

        int lowerBound = (int) Math.ceil(sum / H);

        return new int[]{lowerBound, upperBound};
    }

    private boolean canEatAll(int[] piles, int H, int K) {
        int requiredH = 0;

        for (int count : piles) {
            requiredH += Math.ceil(count / (double) K);
        }

        return requiredH <= H;
    }

    // Time: O(N), 3ms
    // time wise, it is faster than binary search, but use binary search for interview
    public int minEatingSpeed0(int[] piles, int H) {
        int[] bounds = getBounds(piles, H);
        int left = bounds[0];
        int right = bounds[1];

        for (int K = left; K <= right; K++) {
            if (canEatAll(piles, H, K)) {
                return K;
            }
        }

        // Can't reach this
        return -1;
    }

}