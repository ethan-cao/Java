package algorithm.leetCode;

/*
https://leetcode.com/problems/two-sum/

Two Sum

You are given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]
Constraints:
	- 2 <= nums.length <= 104
	- -109 <= nums[i] <= 109
	- -109 <= target <= 109
	- Only one valid answer exists.

Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?

*/

public class E_1 {
    // HashMap
    // Time: O(N), Space: O(N)
    public static int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];

        // number -> its index
        Map<Integer, Integer> locationRecorder = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int requiredNum = target - num;

            // first check the required number is in existing locationRecorder
            if (locationRecorder.containsKey(requiredNum)) {
                indices[0] = i;
                indices[1] = locationRecorder.get(requiredNum);
                break;
            }

            // if the required number is not there, record it
            // this must happen in the end, otherwise, the latter duplicate value overrides index, e.g.{3,3} 6
            locationRecorder.put(num, i);
        }

        return indices;
    }

    // Time: O(N), Space: O(N)
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];

        // HashMap
        // number -> its counterpart's index (number + counterpart = target)
        Map<Integer, Integer> locationRecorder = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int requiredNum = target - num;

            if (locationRecorder.containsKey(num)) {
                result[0] = i;
                result[1] = locationRecorder.get(num);
                break;
            }

            locationRecorder.put(requiredNum, i);
        }

        return result;
    }

}
