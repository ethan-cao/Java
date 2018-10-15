/*
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (38.88%)
 * Total Accepted:    1.1M
 * Total Submissions: 2.8M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * Example:
 * 
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * 
 * 
 * 
 * 
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numberToIndex = new TreeMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int value = nums[i];

            if (numberToIndex.containsKey(value)) {
                if (value + value == target){
                    return new int[]{i, numberToIndex.get(value)};
                }
            } else {
                numberToIndex.put(value, i);
            }

        } 
            

        if (numberToIndex.isEmpty()) {
            return new int[0];
        }

        Integer[] validNums = numberToIndex.keySet().toArray(new Integer[]{});
        final int L = validNums.length;

        for (int i = L - 1; i >= 0; --i) {
            for (int j = 0; j < L; ++j) {

                if (validNums[i] + validNums[j] > target) {
                    break;
                }

                if (j >= i) {
                    return new int[0];
                }

                if (validNums[i] + validNums[j] == target) {
                    return new int[]{numberToIndex.get(validNums[j]), numberToIndex.get(validNums[i])};
                }
            }
        }

        return new int[0];
    }
}
