package algorithm.leetCode;

/*
You are given an array of binary strings strs and two integers m and n.
Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
A set x is a subset of a set y if all elements of x are also elements of y.

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100

### Example
Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.

*/

public class M_474 {

	// DP
	// Time: O(N^3), Space: O(N^3)
	public int findMaxForm(String[] strs, int m, int n) {
		int L = strs.length;

		// counts[i][j][k]: size of the largest subset of strs
		// with the first i string and at most j 0's and at most k 1's
		int[][][] size = new int[L][m + 1][n + 1];

		for (int i = 0; i < L; ++i) {
			String s = strs[i];

			int[] count = count(s);

			for (int j = 0; j <= m; ++j) {
				for (int k = 0; k <= n; ++k) {

					int skip = 0; // subset size when skip strs[i] in the subset
					int take = 0; // subset size when take strs[i] in the subset

					if (i == 0) {
						// BASE
						skip = 0;
						take = j >= count[0] && k >= count[1] ? 1 : 0;
					} else {
						// TRANSFORM
						skip = sizes[i - 1][j][k];
						take = j >= count[0] && k >= count[1]
								? sizes[i - 1][j - count[0]][k - count[1]] + 1
								: sizes[i - 1][j][k];
					}

					sizes[i][j][k] = Math.max(skip, take);
				}
			}
		}

		return sizes[L - 1][m][n];
	}

	// DP, condensed space
	// Time: O(N^3), Space: O(N^2)
	public int findMaxForm1(String[] strs, int m, int n) {
		int L = strs.length;

		// sizes[i][j][k]: size of the largest subset of strs
		// with the first i string and at most j 0's and at most k 1's
		int[][] sizes = new int[m + 1][n + 1];

		for (int i = 0; i < L; ++i) {
			String s = strs[i];

			int[] counts = count(s);

			// !!! iterate backward, same as 416
			// sizes[i][j][k] relies on sizes[i - 1][j - 0count][k - 1count]
			// when calculate sizes[i][j][k], need to preserve sizes[i - 1][j - 0count][k - 1count]
			// iterate from left to right means 
			// sizes[i][j][k] relies on sizes[i][j - 0count][k - 1count], which is wrong
			for (int j = m; j >= 0; --j) {
				for (int k = n; k >= 0; --k) {

					int skip = 0; // subset size when skip strs[i] in the subset
					int take = 0; // subset size when take strs[i] in the subset

					if (i == 0) {
						// BASE
						skip = 0;
						take = counts[0] <= j && counts[1] <= k ? 1 : 0;
					} else {
						// TRANSFORM
						skip = sizes[j][k];
						take = counts[0] <= j && counts[1] <= k
							? sizes[j - counts[0]][k - counts[1]] + 1
							: sizes[j][k];
					}

					sizes[j][k] = Math.max(skip, take);
				}
			}
		}

		return sizes[m][n];
	}

	private int[] count(String str) {
		int[] counts = new int[2];

		for (char ch : str.toCharArray()) {
			if (ch == '0') {
				counts[0]++;
			} else if (ch == '1') {
				counts[1]++;
			}
		}

		return counts;
	}
}
