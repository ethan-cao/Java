package leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
 *
 * return [0, 1].
 */

class Solution1 {
   public static void main(String[] args) {
       Solution1 solution = new Solution1();

       int[] nums = {-1, -2, -3, -4, -5};
//       int answer[] = solution.twoSum(nums, -8);

//       System.out.println(Arrays.toString(answer));
   }

    public int[] twoSum(int[] nums, int target) {
       // use TreeMap so keys are sorted
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

        // Assume keys are already sorted
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

    /**
     * Think the other way around
     *
     * O(n)
     */
    public int[] twoSum1(int[] nums, int target){
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }

        return result;
    }
}
