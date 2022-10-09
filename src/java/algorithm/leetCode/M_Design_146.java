package algorithm.leetCode;

/*
Design and implement a data structure for Least Recently Used (LRU) cache,
It should support the following operations: get and put. Both in O(1) time complexity

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
                  it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

### Example
LRUCache cache = new LRUCache(2); // capacity is 2
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/

import java.util.HashMap;
import java.util.Map;

public class M_Design_146 {

    class Node {
        int key;
        int value;

        Node previous;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class LRUCache {
        private int capacity;
        private Node head;
        private Node tail;
        private Map<Integer, Node> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();

            this.head = new Node(0, 0);
            this.tail = new Node (0, 0);

            head.next = tail;
            tail.previous = head;
        }

        public int get(int key) {
            if (this.map.containsKey(key)) {
                Node node = this.map.get(key);
                this.remove(node);
                this.insert(node);

                return node.value;
            }

            return -1;
        }

        public void put(int key, int value) {
            if (this.map.containsKey(key)) {
                this.remove(this.map.get(key));
            }

            if (this.map.size() == this.capacity) {
                this.remove(this.tail.previous);
            }

            this.insert(new Node(key, value));
        }

        private void remove(Node node) {
            this.map.remove(node.key);

            node.previous.next = node.next;
            node.next.previous = node.previous;

            node.previous = null;
            node.next = null;
        }

        private void insert(Node node) {
            this.map.put(node.key, node);

            Node headNext = head.next;
            head.next = node;

            node.previous = head;

            headNext.previous = node;
            node.next = headNext;
        }

    }

}
