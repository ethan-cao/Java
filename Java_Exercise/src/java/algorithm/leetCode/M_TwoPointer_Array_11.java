package algorithm.leetCode;

/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

### Example
[1,8,6,2,5,4,8,3,7] ->  49

*/


public class M_TwoPointer_Array_11 {

    public static void main(String... args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height)); //49
    }

    public static int maxArea(int[] height) {
        return 1;
    }

}