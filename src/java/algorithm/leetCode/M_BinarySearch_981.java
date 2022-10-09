package algorithm.leetCode;

/*
Create a timebased key-value store class TimeMap, that supports two operations.
1. set(string key, string value, int timestamp)
    Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)
    Returns a value such that set(key, value, timestamp_prev) was called previously,
    with timestamp_prev <= timestamp.
    If there are multiple such values, it returns the one with the largest timestamp_prev.
    If there are no values, it returns the empty string ("").

All key/value strings are lowercase, and have length in the range [1, 100]
!!!! The timestamps for all TimeMap.set operations are strictly increasing.
1 <= timestamp <= 10^7
TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

### Example
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
kv.get("foo", 1);  // output "bar"
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
kv.set("foo", "bar2", 4);
kv.get("foo", 4); // output "bar2"
kv.get("foo", 5); //output "bar2"

*/

import java.util.*;

public class M_BinarySearch_981 {
}

class TimeMap {

    // 120ms
    private class Node {
        private final String value;
        private final int timestamp;

        Node(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private final String EMPTY = "";

    private Map<String, List<Node>> map = new HashMap<>();

    public TimeMap() {
    }

    public void set(String key, String value, int timestamp) {
        List<Node> list = this.map.getOrDefault(key, new ArrayList<>());
        list.add(new Node(value, timestamp));
        this.map.put(key, list);
    }

    public String get(String key, int timestamp) {
        List<Node> list = this.map.getOrDefault(key, new ArrayList<>());

        if (list.isEmpty()) {
            return EMPTY;
        }

        return search(list, 0, list.size() - 1, timestamp);
    }

    private String search(List<Node> list, int left, int right, int target) {
        String value = EMPTY;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            Node node = list.get(middle);

            if (node.timestamp == target) {
                return node.value;
            } else if (node.timestamp > target) {
                right = middle - 1;
            } else {
                value = node.value;
                left = middle + 1;
            }
        }

        return value;
    }

}
