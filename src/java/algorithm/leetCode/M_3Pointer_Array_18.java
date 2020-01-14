package algorithm.leetCode;

/*
Given an array nums of n integers and an integer target,
are there elements a, b, c, and d in nums such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

The solution set must not contain duplicate quadruplets.

### Example
nums = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[   [-1,  0, 0, 1],
    [-2, -1, 1, 2],
    [-2,  0, 0, 2]  ]

*/

import java.util.*;

public class M_3Pointer_Array_18 {

    public static void main(String... args) {
        List<List<Integer>> lists = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        lists.forEach(System.out::println);
        System.out.println(System.lineSeparator());

        lists = fourSum(new int[]{0, 0, 0, 0}, 0);
        lists.forEach(System.out::println); // [[0,0,0,0]]
        System.out.println(System.lineSeparator());

        lists = fourSum(new int[]{0, 1, 5, 0, 1, 5, 5, -4}, 11);
        lists.forEach(System.out::println); // [[-4,5,5,5],[0,1,5,5]]
        System.out.println(System.lineSeparator());

        lists = fourSum(new int[]{-1, 0, 1, 2, -1, -4}, -1);
        lists.forEach(System.out::println); // [[-4,0,1,2],[-1,-1,0,1]]
        System.out.println(System.lineSeparator());
    }

    // Four Pointer, initial position: a = 0, b = 1, c = 2, d = L -1
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return quadruplets;
        }

        Arrays.sort(nums);

        for (int a = 0; a < nums.length - 3; ++a) { // !!! nums.length - 3: leave room for another b, c, d

            // skip duplicates
            if (a > 0 && nums[a] == nums[a - 1]) continue;

            // if the smallest sum is larger than target, not possible
            if (nums[a] * 4 > target) break;

            // if the largest sum is smaller than target, try next one
            if (nums[a] + nums[nums.length - 1] * 3 < target) continue;

            for (int b = a + 1; b < nums.length - 2; ++b) { //  !!! nums.length - 2 : leave room the c, d

                if (b > a + 1 && nums[b] == nums[b - 1]) continue; // !!! b > a+ 1, do it at least when b is a + 1

                if (nums[a] + nums[b] * 3 > target) break;

                if (nums[a] + nums[b] + nums[nums.length - 1] * 2 < target) continue;

                // now, it is 2 sum
                int c = b + 1;
                int d = nums.length - 1;

                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];

                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));

                        // skip duplicate
                        while (c < d && nums[c] == nums[c + 1]) c++;
                        c++;

                        // skip duplicate
                        while (c < d && nums[d] == nums[d - 1]) d--;
                        d--;
                    } else if (sum < target) {
                        c++;
                    } else {
                        d--;
                    }
                }
            }
        }

        return quadruplets;
    }
}

// General solution for K sum: look for k elements in array to form sum target
// it can be divided into two problems:
//  can be divided into two problems:
//      Reduce K sum problem to K – 1 sum problem until 2 sum problem
//      Solve 2 sum Problem

class K_Sum {

    public static void main(String... args) {

        int[] nums = {0, 1, 5, 0, 1, 5, 5, -4};
        Arrays.sort(nums);
        List<List<Integer>> lists = kSum(nums, 0, 4, 11);
        lists.forEach(System.out::println); //  [[-4,5,5,5], [0,1,5,5]]
        System.out.println(System.lineSeparator());

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums1);
        List<List<Integer>> lists1 = kSum(nums1, 0, 4, -1);
        lists1.forEach(System.out::println); // [[-4,0,1,2], [-1,-1,0,1]]
        System.out.println(System.lineSeparator());
    }

    // !!! make sure nums is sorted
    public static List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (k == 2) {
            // 2 sum
            int i = start;
            int j = nums.length - 1;

            while (i < j) {
                int sum = nums[i] + nums[j];

                if (sum == target) {
                    // Arrays.asList returns a fix sized list view on array, which cannot add new element into it
                    // result.add(Arrays.asList(nums[i], nums[j]));
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    result.add(list);

                    while (i < j && nums[i] == nums[i + 1]) i++;
                    i++;

                    while (i < j && nums[j] == nums[j - 1]) j--;
                    j--;
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        } else {
            // reduce k sum to k-1 sum
            for (int i = start; i < nums.length - (k - 1); ++i) {
                int num = nums[i];

                if (i > start && num == nums[i - 1]) continue;

                if (num * k > target) break;

                if (num + nums[nums.length - 1] * (k - 1) < target) continue;

                List<List<Integer>> lists = kSum(nums, i + 1, k - 1, target - num);
                lists.forEach(list -> list.add(0, num));
                result.addAll(lists);
            }
        }

        return result;
    }

}