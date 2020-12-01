package algorithm.leetCode;

/*
Given a string, sort it in decreasing order based on the frequency of characters.

### Example
"tree" -> "eert"
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

"cccaaa" -> "cccaaa"
"Aabb" -> "bbAa"

*/

import java.util.*;

public class M_Heap_HashMap_451 {

    // Bucket sort, 4ms
    public String frequencySort0(String s) {
        // Strings are encoded as UTF-16. In UTF-16, the ASCII character set is encoded as values [0 - 127]
        int[] counter = new int[128];  // char -> its frequency
        for (char c : s.toCharArray()) {
            counter[c]++;
        }

        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> counter[b] - counter[a]);
        for (int i = 0; i < counter.length; i++) {
            maxHeap.offer(i);
        }

        StringBuilder sb = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            int c = maxHeap.poll();
            int frequency = counter[c]; // if frequency is 0, nothing got added

            for (int i = 0; i < frequency; i++) {
                sb.append( (char) c);
            }
        }

        return sb.toString();
    }

    // Heap, 21ms
    public String frequencySort(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );

        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            heap.offer(entry);
        }

        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> entry = heap.poll();

            char c = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; ++i) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    // Bucket sort, 18ms
    public String frequencySort1(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        List<Character>[] bucket = new List[s.length() + 1];

        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            char c = entry.getKey();
            int frequency = entry.getValue();

            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }

            // use list to hold different chat that appear with the same frequency
            List<Character> list = bucket[frequency];

            list.add(c);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = bucket.length - 1; i >= 0; --i) {
            List<Character> list = bucket[i];

            if (list == null) {
                continue;
            }

            for (char c : list) {
                for (int j = 0; j < i; ++j) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
