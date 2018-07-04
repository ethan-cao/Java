package algorithm.colllection;

/**
 * collection, whose remove operation delete the largest(max-originated) or smallest(min-originated) item.
 * <p>
 * this priorityQueue is using Binary heap for implementation
 * <p>
 * Binary heap: Array representation of heap-ordered complete binary tree.
 * 1) leave a[0] empty, since it will be easy to calculate
 * 2) Largest key is a[1], the root
 * 3) Parent of node at k is at k/2.
 * 4) Children of node at k are at 2k and 2k+1.
 * 5) Heap-ordered : Parent's key no smaller than children's keys
 */
public class PriorityQueueTest<Key extends Comparable<Key>> {
    private final Key[] tree;  // TODO use resizing array,  Key should immutable
    private int size;

    public static void main(String[] args) {
        PriorityQueueTest queue = new PriorityQueueTest(16);
    }

    public PriorityQueueTest(int initialCapacity) {
        this.tree = (Key[]) new Comparable[initialCapacity];
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }

    public Key max() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.tree[1];
    }

    public void insert(Key key) {
        if (this.size() >= this.tree.length) {
            return;
        }

        int newPosition = this.size() + 1;
        this.tree[newPosition] = key;
        this.swim(newPosition);
    }

    public Key deleteMax() {
        Key max = max();

        this.exchange(1, this.size());

        this.tree[this.size()] = null;
        this.size--;

        this.sink(1);

        return max;
    }

    // move node that is larger than its parent to appropriate position
    private void swim(int k) {
        int parentKey = k / 2;
        while (k > 1 && this.tree[parentKey].compareTo(this.tree[k]) < 0) {
            this.exchange(parentKey, k);
            k = parentKey;
            parentKey = k / 2;
        }
    }

    // move node that is smaller than its children to appropriate position
    private void sink(int k) {
        int leftChildKey = 2 * k;
        int rightChildKey = leftChildKey + 1;

        while (rightChildKey <= this.size()) {
            int largerOne = this.tree[leftChildKey].compareTo(this.tree[rightChildKey]) > 0 ? leftChildKey : rightChildKey;

            if (this.tree[largerOne].compareTo(this.tree[k]) > 0) {
                this.exchange(largerOne, k);
                k = largerOne;

                leftChildKey = 2 * k;
                rightChildKey = leftChildKey + 1;
            } else {
                break;
            }
        }
    }

    private void exchange(int i, int j) {
        Key temp = this.tree[i];
        this.tree[i] = this.tree[j];
        this.tree[j] = temp;
    }
}
