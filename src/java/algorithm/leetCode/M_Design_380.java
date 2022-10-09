package algorithm.leetCode;

/*
Design a data structure that supports all following operations in average O(1) time.
    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements.
               Each element must have the same probability of being returned.

### Example
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();

*/

import java.util.*;

public class M_Design_380 {

    public static void main(String... args) {
        RandomizedSet obj = new RandomizedSet();

        int val = 1;
        boolean param_1 = obj.insert(val);
        boolean param_2 = obj.remove(val);

        int param_3 = obj.getRandom();
    }

}

// 8ms, duplicate not allowed
class RandomizedSet {
    private Random random = new Random();
    private List<Integer> nums = new ArrayList<>();
    private Map<Integer, Integer> positions = new HashMap<>();

    // Initialize your data structure here.
    public RandomizedSet() {
    }

    // Inserts a value to the set. Returns true if the set did not already contain the specified element.
    public boolean insert(int val) {
        if (this.positions.containsKey(val)) {
            return false;
        }

        this.nums.add(val);
        this.positions.put(val, this.nums.size() - 1);
        return true;
    }

    // Removes a value from the set. Returns true if the set contained the specified element.
    public boolean remove(int val) {
        if (!this.positions.containsKey(val)) {
            return false;
        }

        int position = this.positions.get(val);

        Collections.swap(this.nums, position, this.nums.size() - 1);
        this.positions.put(this.nums.get(position), position);

        this.nums.remove(this.nums.size() - 1);
        this.positions.remove(val);

        return true;
    }

    // Get a random element from the set.
    public int getRandom() {
        int randomPosition = this.random.nextInt(this.nums.size());
        return this.nums.get(randomPosition);
    }
}

// 8ms, duplicate not allowed
class RandomizedSet1 {
    private Random random = new Random();
    private List<Integer> nums = new ArrayList<>();
    private Map<Integer, Set<Integer>> positions = new HashMap<>();

    public RandomizedSet1() {
    }

    public boolean insert(int val) {
        this.nums.add(val);

        // positions for val
        Set<Integer> positions = this.positions.compute(val, (k, v) -> v == null ? new HashSet<Integer>() : v);
        positions.add(this.nums.size() - 1);

        return true;
    }

    public boolean remove(int val) {
        if (!this.positions.containsKey(val)) {
            return false;
        }

        // positions for val
        Set<Integer> positions = this.positions.get(val);
        // the one to be removed
        int position = positions.iterator().next();

        Collections.swap(this.nums, position, this.nums.size() - 1);
        Set<Integer> positionsToUpdate = this.positions.get(this.nums.get(position));
        positionsToUpdate.remove(this.nums.size() - 1);
        positionsToUpdate.add(position);

        this.nums.remove(this.nums.size() - 1);
        positions.remove(val);

        return true;
    }

    public int getRandom() {
        int randomPosition = this.random.nextInt(this.nums.size());
        return this.nums.get(randomPosition);
    }

}
