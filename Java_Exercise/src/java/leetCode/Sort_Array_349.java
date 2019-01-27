package leetCode;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Given two arrays, write a function to compute their intersection.

### Input
int[] nums1, int[] nums2

### Output
int[]

### Example
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

### Condition
Each element in the result must be unique.
The result can be in any order.

### Essential problem

### Corner case

*/
public class Sort_Array_349 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        System.out.println(intersection(nums1, nums2));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> result = new HashSet<>();

        if (null == nums1 || null == nums2 || 0 == nums1.length || 0 == nums2.length) {
            return new int[0];
        }

        HashSet<Integer> set1 = Arrays.stream(nums1)
                                        .boxed()
                                        .collect(Collectors.toCollection(HashSet::new));

//        if ()

        return new int[2];
    }

    public static int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums1).distinct().filter(e-> set.contains(e)).toArray();
    }
}
