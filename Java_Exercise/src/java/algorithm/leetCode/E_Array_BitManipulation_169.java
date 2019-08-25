package algorithm.leetCode;

/*
Given an array of size n, find the majority element.
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that
    the array is non-empty
    the majority element always exist in the array.

### Example
[3,2,3] -> 3

[2,2,1,1,1,2,2] --> 2

*/


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E_Array_BitManipulation_169 {

    public static void main(String... args) {
        int[] a = {3, 2, 3};
//        int[] a = {2, 2, 1, 1, 1, 2, 2};

        System.out.println(majorityElement2(a));
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> tally = new HashMap<>();
        int half = nums.length / 2;

        for (int i : nums) {
            int occurrence = tally.getOrDefault(i, 0) + 1;
            tally.put(i, occurrence);

            if (occurrence > half) {
                return i;
            }
        }

        return 0;
    }

    public static int majorityElement0(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // Boyer–Moore majority vote algorithm,  O(n)
    // https://www.zhihu.com/question/49973163/answer/235921864
    public static int majorityElement1(int[] nums) {
        int majority = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; ++i) {
            if (count == 0) {
                majority = nums[i];
                count++;
            } else if (majority == nums[i]){
                count++;
            } else {
                count--;
            }
        }

        return majority;
    }

    // Bit manipulation
    public static int majorityElement2(int[] nums) {
        int[] bit = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; ++i) {
                if ((num >> i & 1) == 1) {
                    bit[i]++;
                }

            }
        }

        int majority = 0;
        int half = nums.length / 2;

        for (int i = 0; i < 32; ++i) {
            bit[i] = bit[i] > half ? 1 : 0;
            majority += bit[i] * (1 << i);
        }

        return majority;
    }
}