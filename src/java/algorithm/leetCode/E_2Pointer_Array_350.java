package algorithm.leetCode;


/*
Given two arrays, write a function to logStar their intersection.
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.


### Example
nums1 = [1,2,2,1], nums2 = [2,2] -> [2,2]
nums1 = [4,9,5], nums2 = [9,4,9,8,4] -> [4,9]

*/

import java.util.*;

public class E_2Pointer_Array_350 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        // output [2, 2]

//        int[] nums1 = {4, 9, 5};
//        int[] nums2 = {9, 4, 9, 8, 4};
        // output [4, 9]

//        int[] nums1 = {1, 1};
//        int[] nums2 = {1, 2, 1, 1, 1};
        // output [1, 1]

//        int[] nums1 = {9, 8, 8, 9, 8};
//        int[] nums2 = {9, 4, 9, 8, 4};
        // output [9, 9, 8]

        System.out.println(Arrays.toString(intersection1(nums1, nums2)));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2 || 0 == nums1.length || 0 == nums2.length) {
            return new int[0];
        }

        Integer[] numsInt1 = Arrays.stream(nums1).boxed().toArray(Integer[]::new);
        List<Integer> list1 = new ArrayList<>();
        Collections.addAll(list1, numsInt1);

        Integer[] numsInt2 = Arrays.stream(nums2).boxed().toArray(Integer[]::new);
        List<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2, numsInt2);

        List<Integer> listNew1 = list1.size() > list2.size() ? list2 : list1; // pick smaller one to iterate
        List<Integer> listNew2 = list1.size() > list2.size() ? list1 : list2;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < listNew1.size(); ++i) {
            Integer number = listNew1.get(i);

            if (listNew2.contains(number)) {
                result.add(number);
                listNew2.remove(listNew2.indexOf(number));
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] intersection1(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2 || 0 == nums1.length || 0 == nums2.length) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int idx1 = 0;
        int idx2 = 0;

        int l = Math.min(nums1.length, nums2.length);
        int idx3 = 0;

        int[] intersection = new int[l];
        while (idx1 < nums1.length && idx2 < nums2.length) {

            if (nums1[idx1] == nums2[idx2]) {
                intersection[idx3] = nums1[idx1];
                idx1++;
                idx2++;
                idx3++;
            } else {
                if (nums1[idx1] > nums2[idx2]) {
                    idx2++;
                } else {
                    idx1++;
                }
            }
        }

        int[] result = new int[idx3];
        System.arraycopy(intersection, 0, result, 0, idx3);

        return result;
    }
}
