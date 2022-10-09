package algorithm.leetCode;

/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container,
such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

### Example
[1,8,6,2,5,4,8,3,7] ->  49

*/

public class M_2Pointer_Array_11 {

    public static void main(String... args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})); //49
        System.out.println(maxArea(new int[]{10, 5, 4, 48, 3, 37, 0, 1, 231, 3, 211})); //422
    }

    // 2 Pointer, Time O(N), 2ms
    public static int maxArea(int[] height) {
        int maxArea = 0;

        if (height == null || height.length == 0) {
            return maxArea;
        }

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            // the smaller height determines the rectangle height
            // there is no point in moving the larger height forward, since the area is only getting smaller
            // just try moving the smaller height forward, see if could get a bigger area
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    // brute force
    public static int maxArea1(int[] height) {
        int maxArea = 0;

        if (height == null || height.length == 0) {
            return maxArea;
        }

        for (int startIndex = 0; startIndex < height.length - 1; ++startIndex) {
            for (int endIndex = startIndex + 1; endIndex < height.length; ++endIndex) {

                int area = Math.min(height[startIndex], height[endIndex]) * (endIndex - startIndex);
                maxArea = Math.max(area, maxArea);
            }

        }

        return maxArea;
    }

}