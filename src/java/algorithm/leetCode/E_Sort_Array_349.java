package algorithm.leetCode;

/*
Given two arrays, write a function to logStar their intersection.
Each element in the result must be unique.
The result can be in any order.

### Example
nums1 = [1,2,2,1], nums2 = [2,2] -> [2]
nums1 = [4,9,5], nums2 = [9,4,9,8,4] -> [9,4]

*/

import java.util.*;
import java.util.stream.Collectors;

public class E_Sort_Array_349 {

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 2, 1};
//        int[] nums2 = {2, 2};

        int[] nums1 = {4,9,5}; int[] nums2 = {9, 4, 9, 8,4};


        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2 || 0 == nums1.length || 0 == nums2.length) {
            return new int[0];
        }

        HashSet<Integer> result = new HashSet<>();

        HashSet<Integer> set1 = new HashSet<>();
        for (int i = 0; i < nums1.length; ++i) {
            set1.add(nums1[i]);
        }

        for (int j = 0; j < nums2.length; ++j) {
            int number = nums2[j];

            if (set1.contains(number)) {
                result.add(number);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums1).distinct().filter(set::contains).toArray();
    }
}
