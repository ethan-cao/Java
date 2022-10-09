package algorithm.leetCode;

/*
Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:

For i <= k < j:
arr[k] > arr[k + 1] when k is odd, and
arr[k] < arr[k + 1] when k is even.
Or, for i <= k < j:
arr[k] > arr[k + 1] when k is even, and
arr[k] < arr[k + 1] when k is odd.

1 <= arr.length <= 4 * 10^4
0 <= arr[i] <= 10^9

### Example
[4,8,12,16] -> 2
[100] -> 1
[9,4,2,10,7,8,8,1,9] -> 5
Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]

*/

import java.util.Arrays;

public class M_DP_978 {

    public static void main(String[] args) {
        System.out.println(maxTurbulenceSize(new int[]{4, 8, 12, 16}));  // 2
    }

    public static int maxTurbulenceSize(int[] arr) {
        return 1;
    }
}