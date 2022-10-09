package algorithm.leetCode;

/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),

Prove that at least one duplicate number must exist.
Answer : there are n distinctive value, there are n + 1 spots, there must be at least 1 duplicate number

Assume that there is only one duplicate number, but it could be repeated more than once.
find the duplicate one without modifying the array

### Example
[1,3,4,2,2] --> 2
[3,1,3,4,2] --> 3

*/

import java.util.*;

public class M_2Pointer_BinarySearch_Array_287 {

    public static void main(String... args) {
        System.out.println(findDuplicate2(new int[]{2, 2, 2, 2, 2})); // 2
        System.out.println(findDuplicate2(new int[]{1, 3, 4, 2, 2})); // 2
        System.out.println(findDuplicate2(new int[]{3, 1, 3, 4, 2})); // 3
    }

    // List, Cycle
    // Time: O(N), Space: O(1), 1ms
    public static int findDuplicate2(int[] nums) {
        if (nums.length == 2) {
            return nums[0];
        }

        // !!! consider the number in the array as reference to the index of next node
        // since there is 1 duplicate number, there is cycle and duplicate one is the entry of the cycle
        // [1,3,4,2,2]: 1->3->2->4->2      cycle: 2->4->2
        // [3,1,3,4,2]: 3->4->2->3->4->2   cycle: 3->4->2->3
        // so we just need to find the entry of the cycle

        // Given Floyd's algorithm for cycle detection, use slowPointer and fastPointer with velocity 1 and 2
        // they will meet inside the cycle, which is when fastPointer == slowPointer

        // then set fastPointer to beginning and move 1 step each time, move slowPointer 1 step each time
        // they will meet at the entry of the cycle
        // Proof :
        //  length of cycle is C, length from start to cycle entry is x, length from the entry to meeting point is y
        //  when they meet 1st time, let's say slowPointer moved s steps, fastPointer moved 2s steps
        //  since 2s = s + n * C (fastPointer moved several full cycles) ==> s = n * C
        //  and s = x + y
        //  so x + y = n * C ==> x = n * C - y = (n - 1) * C + (C - y)
        //
        //  x = (n - 1) * C + (C - y) (several full cycle + step from meeting point to cycle entry)
        //   means
        //  2 pointer with same velocity move from the start and the meeting point respectively, will meet in cycle entry

        // !!! value in nums[] is the reference to the next one
        int slowPointer = nums[0];        // moves 1 step each time
        int fastPointer = nums[nums[0]];  // moves 2 step each time

        // move them until they meet
        while (slowPointer != fastPointer) {
            slowPointer = nums[slowPointer];
            fastPointer = nums[nums[fastPointer]];
        }

        // !!!! reset fastPointer to 0 and move them with same speed
        // not nums[0], since the pointer needs to travel from start to the cycle entry
        fastPointer = 0;
        // Alternatively, fastPointer = nums[0]; slowPointer = nums[slowPointer];

        while (slowPointer != fastPointer) {
            slowPointer = nums[slowPointer];
            fastPointer = nums[fastPointer];
        }

        return fastPointer;
    }

    // Binary Search, this works iff there is only 1 duplicate
    // Time: O(NlogN), Space: O(1), 1ms
    public static int findDuplicate1(int[] nums) {
        // !!! forget about array, do binary search for the value, given we know there is only 1 duplicate number
        // left <= num[i] <= right
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int smallEqualCount = count(nums, middle);

            // if there is no duplicate, values are evenly distributed
            // since we know this is 1 duplicate, the duplicate one is either <= middle or > middle

            // there are count elements in value range [1, middle], (n+1-count) elements in value range [middle+1, n]
            // that means count elements in middle spots, and (n+1-count) elements in (n-middle) spots

            if (smallEqualCount > middle) {
                //  more elements than spots on left side, duplicate in [left, middle]
                right = middle - 1;
            } else {
                //  more elements than spots on right side, duplicates in [middle+1, right]
                left = middle + 1;
            }
        }

        return left;
    }

    private static int count(int[] nums, int target) {
        int counter = 0;
        for (int num : nums) {
            if (num <= target) {
                counter++;
            }
        }
        return counter;
    }

    // Time: O(N), Space: O(N), 4ms
    public static int findDuplicate0(int[] nums) {
        Set<Integer> appearedNums = new HashSet<>();

        for (int num : nums) {
            if (appearedNums.contains(num)) {
                return num;
            } else {
                appearedNums.add(num);
            }
        }

        return -1;
    }


}