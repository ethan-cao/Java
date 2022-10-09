package algorithm.leetCode;

/*
Given a non-empty array of integers, return the k most frequent elements.

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.

### Example
nums = [1,1,1,2,2,3], k = 2 -> [1,2]
nums = [1], k = 1 -> [1]

*/

import java.util.*;

public class M_Heap_HashMap_347 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    // Bucket sort, 7ms
    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

        for (int n : nums) {
            int frequency = frequencies.getOrDefault(n, 0);
            frequencies.put(n, frequency + 1);
        }

        for (int num : frequencies.keySet()) {
            int frequency = frequencies.get(num);

            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }

            bucket[frequency].add(num);
        }

        int count = 0;
        for (int idx = bucket.length - 1; idx >= 0; idx--) {
            if (bucket[idx] == null) {
                continue;
            }

            for (Integer number : bucket[idx]) {
                result[count] = number;
                count++;
            }

            if (count == k) {
                break;
            }
        }

        return result;
    }

    // HashMap, 9ms
    public static int[] topKFrequent1(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> frequencies = new HashMap<>();
        TreeMap<Integer, List<Integer>> sortedFrequency = new TreeMap<>();

        for (int num : nums) {
            int count = frequencies.getOrDefault(num, 0);
            frequencies.put(num, count + 1);
        }

        for (int num : frequencies.keySet()) {
            int frequency = frequencies.get(num);

            if (!sortedFrequency.containsKey(frequency)) {
                sortedFrequency.put(frequency, new ArrayList<>());
            }

            sortedFrequency.get(frequency).add(num);
        }

        int count = 0;
        while (count < k) {
            Map.Entry<Integer, List<Integer>> entry = sortedFrequency.pollLastEntry();

            for (Integer i : entry.getValue()) {
                result[count] = i;
                count++;
            }
        }

        return result;
    }

    // Heap, 9ms
    public static int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );

        for (Map.Entry<Integer, Integer> entry: counter.entrySet()) {
            minHeap.offer(entry);
        }

        int[] result = new int[k];
        for(int i = 0; i < k; ++i) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }

}
