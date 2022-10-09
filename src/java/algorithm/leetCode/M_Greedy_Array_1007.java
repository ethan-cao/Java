package algorithm.leetCode;

/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
(A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations, so all the values in A are the same,
or all the values in B are the same
If it cannot be done, return -1.

1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000

### Example
A = [2,1,2,4,2,2], B = [5,2,6,2,3,2] ->  2
The first figure represents the dominoes as given by A and B: before we do any rotations.
If rotate the second and fourth dominoes, we can make every value in the top row equal to 2,
as indicated by the second figure.

A = [3,5,1,2,3], B = [3,6,3,3,4] -> -1
impossible to rotate the dominoes to make one row of values equal.

*/

import java.util.*;

public class M_Greedy_Array_1007 {

    public static void main(String... args) {
        System.out.println(minDominoRotations1(new int[]{2}, new int[]{1}));                                   // 0
        System.out.println(minDominoRotations1(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}));     // 2
        System.out.println(minDominoRotations1(new int[]{3, 5, 1, 2, 3}, new int[]{3, 6, 3, 3, 4}));           // -1
        System.out.println(minDominoRotations1(new int[]{1, 2, 3, 4, 6}, new int[]{6, 6, 6, 6, 5}));           // 1
        System.out.println(minDominoRotations1(new int[]{3, 5, 1, 2, 3}, new int[]{3, 6, 3, 3, 4}));           // -1
        System.out.println(minDominoRotations1(new int[]{2, 1, 1, 1, 2, 2, 2, 1, 1, 2}, new int[]{1, 1, 2, 1, 1, 1, 1, 2, 1, 1}));    // 2
    }

    // Time: O(N), Space: O(N)
    // too slow, 77 ms
    public static int minDominoRotations0(int[] A, int[] B) {
        int N = A.length;

        if (N == 1) {
            return 0;
        }

        int HALF = (int) Math.ceil(N / 2.0);  // !!! divided by 2.0 to get double !!!
        int modeA = 0;// mode : 众数
        int modeB = 0;
        Map<Integer, Integer> counterA = new HashMap<>();
        Map<Integer, Integer> counterB = new HashMap<>();

        for (int i = 0; i < N; ++i) {
            counterA.compute(A[i], (k, v) -> v == null ? 1 : v + 1);
            if (counterA.get(A[i]) >= HALF) {
                modeA = A[i];
            }

            counterB.compute(B[i], (k, v) -> v == null ? 1 : v + 1);
            if (counterB.get(B[i]) >= HALF) {
                modeB = B[i];
            }
        }

        if (modeA == 0 && modeB == 0) {
            return -1;
        }

        int modeAOccurrence = counterA.getOrDefault(modeA, 0) + counterB.getOrDefault(modeA, 0);
        int modeBOccurrence = counterA.getOrDefault(modeB, 0) + counterB.getOrDefault(modeB, 0);
        int mode = modeAOccurrence > modeBOccurrence ? modeA : modeB;
        int minimumRotationCount = 0;
        int[] base = counterA.get(mode) > counterB.get(mode) ? A : B;
        int[] reference = counterA.get(mode) > counterB.get(mode) ? B : A;

        for (int i = 0; i < N; ++i) {
            if (base[i] != mode) {
                swap(A, B, i);
                minimumRotationCount++;

                if (base[i] != mode) {
                    return -1;
                }
            }
        }

        return minimumRotationCount;
    }

    private static void swap(int[] a, int[] b, int i) {
        int temp = a[i];
        a[i] = b[i];
        b[i] = temp;
    }

    // Time: O(N)
    // 6ms
    public static int minDominoRotations(int[] A, int[] B) {
        final int SIZE = A.length;

        // !!! since we need to count value ∈ [1,6], use array is better than Map
        int[] counterA = new int[6];
        int[] counterB = new int[6];
        int[] counterCommon = new int[6];

        // count occurrence for each value
        for (int i = 0; i < SIZE; ++i) {
            counterA[A[i] - 1]++;
            counterB[B[i] - 1]++;

            if (A[i] == B[i]) {
                counterCommon[A[i] - 1]++;
            }
        }

        // SIZE + counterCommon[i] : number of slots that have different value (dismiss slots that have identical value)
        // this is the minimal occurrence for a value to make either A or B have the same value
        for (int i = 0; i < 6; ++i) {
            if (counterA[i] + counterB[i] - counterCommon[i] * 2 >= SIZE - counterCommon[i]) {
                // the more the value occurs, the less the rotation is required
                return SIZE - Math.max(counterA[i], counterB[i]);
            }
        }

        return -1;
    }

    // 4ms
    public static int minDominoRotations1(int[] A, int[] B) {
        int rotationCount = Integer.MAX_VALUE;

        // inspect each possible value
        for (int target = 1; target <= 6; ++target) {
            rotationCount = Math.min(rotationCount, getRotationCount(A, B, target));
            rotationCount = Math.min(rotationCount, getRotationCount(B, A, target));
        }

        return rotationCount == Integer.MAX_VALUE ? -1 : rotationCount;
    }

    // make all item in base identical
    private static int getRotationCount(int[] base, int[] reference, int target) {
        int count = 0;

        for (int i = 0; i < base.length; ++i) {
            // if both are different than target, not possible
            if (base[i] != target && reference[i] != target) {
                return Integer.MAX_VALUE;
            }

            // the base[i] is not target, but reference is target, swapping works
            if (base[i] != target && reference[i] == target) {
                count++;
            }
        }

        return count;
    }

}