package algorithm.leetCode;

/*
Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
You may assume k is always valid, 1 ≤ k ≤ array's length.

### Example
[3,2,1,5,6,4] and k = 2 --> 5
[3,2,3,1,2,4,5,5,6] and k = 4 --> 4

*/

import java.util.*;

public class M_Sort_Array_215 {

    public static void main(String... args) {
        System.out.println(findKthLargest2(new int[]{3, 2, 1, 5, 6, 4}, 2)); // 5
        System.out.println(findKthLargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)); // 4
        System.out.println(findKthLargest2(new int[]{5, 4, 3, 2, 1}, 2)); // 4
    }

    // 1ms
    // Time: O(NlogN), space: O(1)
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // Heap, 6ms
    // Time: O(NlogK), Space:O(K)
    public static int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int num : nums) {
            heap.offer(num);
        }

        while (heap.size() > k) {
            heap.poll();
        }

        return heap.poll();
    }

    // Quick Select, 2ms
    // Time: O(N^2), Space:O(K)
    // ref: algorithm.search.Selection.QuickSelect
    public static int findKthLargest2(int[] nums, int k) {
//        knuthShuffle(nums);

        int targetIdx = nums.length - k; // when nums are in sorted order
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int partitionKeyIndex = partition(nums, start, end);

            if (partitionKeyIndex > targetIdx) {
                end = partitionKeyIndex - 1;
            } else if (partitionKeyIndex < targetIdx) {
                start = partitionKeyIndex + 1;
            } else {
                return nums[partitionKeyIndex];
            }
        }

        // start === end == targetIndex
        return nums[start];
    }

    private static int partition(int[] nums, int start, int end) {
        int partitionKeyIdx = start;

        while (start <= end) {
            while (start <= end && nums[start] <= nums[partitionKeyIdx]) {
                start++;
            }

            while (start <= end && nums[end] > nums[partitionKeyIdx]) {
                end--;
            }

            if (start > end) {
                break;
            }

            swap(nums, start, end);
        }

        swap(nums, partitionKeyIdx, end);

        return end;
    }

    // algorithm.math.Shuffle.knuthShuffle
    private static void knuthShuffle(int[] data) {
        final Random random = new Random();

        for (int i = 1; i < data.length; i++) {
            int j = random.nextInt(i);
            swap(data, i, j);
        }
    }

    private static void swap(int[] data, int idx1, int idx2) {
        int swap = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = swap;
    }

    // QuickSelect using 1st element as partitionKey, 0ms
    public int findKthLargest3(int[] nums, int k) {
        return find(nums, 0, nums.length - 1, k);
    }

    private int find(int[] nums, int start, int end, int k) {
        int partitionKeyIdx = start + (end - start) / 2;

        int i = start;
        int j = end;

        while (i <= j) {
            while (i <= j && nums[i] > nums[partitionKeyIdx]) {
                i++;
            }

            while (i <= j && nums[j] < nums[partitionKeyIdx]) {
                j--;
            }

            if (i > j)  {
                break;
            }

            swap(nums, i, j);
            i++;
            j--;
        }

        if (start + k - 1 <= j) {
            return find(nums, start, j, k);
        }

        if (start + k - 1 >= i) {
            return find(nums, i, end, k - (i - start));
        }

        return nums[i - 1];
    }

}
