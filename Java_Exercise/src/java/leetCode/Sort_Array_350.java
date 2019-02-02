package leetCode;

import edu.princeton.cs.algs4.In;

import java.util.*;

/*
Given two arrays, write a function to compute their intersection.

### Input
int[] nums1, int[] nums2

### Output
int[]

### Example
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]


num1 = {1,1}
num2 = {1,2,1,1,1}
--> {1, 1}

### Condition
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.


### Essential problem

### Corner case

*/
public class Sort_Array_350 {

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 2, 1};
//        int[] nums2 = {2, 2};

//        int[] nums1 = {4, 9, 5};
//        int[] nums2 = {9, 4, 9, 8, 4};

//        int[] nums1 = {1, 1};
//        int[] nums2 = {1, 2, 1, 1, 1};

        int[] nums1 = {9, 8, 8};
        int[] nums2 = {9, 4, 9, 8, 4};

        System.out.println(Arrays.toString(intersection(nums1, nums2)));
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

        for (int i = 0 ; i < listNew1.size(); ++i){
            Integer number = listNew1.get(i);

            if (listNew2.contains(number)){
                result.add(number);
                listNew2.remove(listNew2.indexOf(number));
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] intersection1(int[] nums1, int[] nums2) {

        return new int[1];
    }

}
