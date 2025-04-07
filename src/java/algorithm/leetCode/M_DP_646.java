package algorithm.leetCode;

/*
You are given an array of n pairs where pairs[i] = [left_i, right_i] and left_i < right_i.
A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
Return the length longest chain which can be formed.
You do not need to use up all the given intervals. You can select pairs in any order.

1 <= n <= 1000
-1000 <= left_i < right_i <= 1000

### Example
pairs = [[1,2],[2,3],[3,4]] -> 2
Explanation: The longest chain is [1,2] -> [3,4].

pairs = [[1,2],[7,8],[4,5]] -> 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].

*/

import java.util.Arrays;

public class M_DP_646 {

    // DP, 36ms
    // Time: O(n^2), Space: O(n)
    public int findLongestChain1(int[][] pairs) {
        int L = pairs.length;

        // counts[i]: length of the longest chain ending at pairs[i]
        int[] counts = new int[L];
        Arrays.fill(counts, 1);
    
        int maxCount = 1;
    
        // Sort pairs by pair[right]
        Arrays.sort(pairs, (p1, p2) -> Integer.compare(p1[1], p2[1]));
        // alternatively, you can also sort the pair[left]
        // Arrays.sort(pairs, (p1, p2) -> Integer.compare(p1[0], p2[0]));
    
        for (int i = 1; i < L; ++i) {
            for (int j = i - 1; j >= 0; --j) {
            // alternatively
            // for (int j = 0; j < i; ++j) {

                // For each pair i, look at all previous pairs j < i. If pairs[j][1] < pairs[i][0], 
                // we can extend the chain from j to i, so counts[i] can be counts[j] + 1.
                if (pairs[j][1] < pairs[i][0]) {
                    counts[i] = Math.max(counts[i], counts[j] + 1);
                }

                maxCount = Math.max(maxCount, counts[i]);
            }
        }
    
        return maxCount;
    }

    // Greedy, 26ms
    // Time: O(NlogN)
    public static int findLongestChain2(int[][] pairs) {
        // sorty by the pari[right]
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Set currentEnd to a value less than the minimum left_i (-1000)
        int currentEnd = -1001;
        int chainLength = 0;
        
        for (int[] pair : pairs) {
            // If current pair's start is greater than the last end, add it to chain
            if (pair[0] > currentEnd) {
                chainLength++;        // Increment chain length
                currentEnd = pair[1]; // Update current end to this pair's end
            }
        }
        
        return chainLength;
    }

}
