package algorithm.dataStructure;

/**
 * Heap : complete tree
 *
 * (max) Binary heap: heap-ordered complete binary tree, implemented by array
 * * make a[0] empty, store element from index 1, since it will be easy to calculate key of parent and children
 * * Parent of node at k is at k/2.
 * * Children of node at k are at 2k(left) and 2k+1(right)
 * * Heap-ordered : parent's key >= children's key
 * * Largest key is nodes[1], the root
 *
 * for min binary heap, the smallest one is the root a [1]
 */


public class BinaryHeap<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        // Use Binary heap to implement priorityQueue,
        // whose remove operation delete the largest(max-originated) or smallest(min-originated) item.
        BinaryHeap bh = new BinaryHeap(32);
    }

    private final Key[] nodes;  // if using resizing array,  Key should immutable
    private int size;

    public BinaryHeap(int initialCapacity) {
        this.nodes = (Key[]) new Comparable[initialCapacity + 1];  // fixed capacity for simplicity
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size; // maximal value for size is initialCapacity, which is length - 1
    }

    public Key max() {
        return isEmpty() ? null : this.nodes[1]; // root
    }

    public void insert(Key key) {
        // since index 0 is empty, maximal value for size is initialCapacity, which is length - 1
        if (this.size() >= this.nodes.length) {
            return;
        }

        int newIndex = this.size() + 1;
        this.nodes[newIndex] = key;
        this.size++;

        this.swim(newIndex);
    }

    public Key deleteMax() {
        Key max = max();

        this.exchange(1, this.size());
        this.nodes[this.size()] = null;
        this.size--;

        // At most 2lgN compares
        this.sink(1);

        return max;
    }

    // move node to sorted position, if it is larger than its parent
    private void swim(int index) {
        int parentIndex = index / 2;

        while (index > 1 && compare(parentIndex, index) < 0) {
            this.exchange(parentIndex, index);
            index = parentIndex;
            parentIndex = index / 2;
        }
    }

    // move node to sorted position, if it is smaller than its children
    private void sink(int index) {
        int leftChildIndex = 2 * index;
        int rightChildIndex = leftChildIndex + 1;

//        while (rightChildIndex <= this.size()) {
        while (leftChildIndex <= this.size()  ) {  // make sure at least 1 node is there, then we can check further
            int largerIndex = compare(leftChildIndex, rightChildIndex) > 0 ? leftChildIndex : rightChildIndex;

            if (compare(largerIndex, index) > 0) {
                this.exchange(largerIndex, index);
                index = largerIndex; // nodes[i] has been moved to nodes[largerIndex]

                leftChildIndex = 2 * index;
                rightChildIndex = leftChildIndex + 1;
            } else {
                break;
            }
        }
    }

    private int compare(int index1, int index2) {
        if (index1 > this.size()) { // if there is no value corresponding to the index
            return -1;
        }

        if (index2 > this.size()) {
            return 1;
        }

        return this.nodes[index1].compareTo(this.nodes[index2]);
    }

    private void exchange(int index1, int index2) {
        Key swap = this.nodes[index1];
        this.nodes[index1] = this.nodes[index2];
        this.nodes[index2] = swap;
    }
}