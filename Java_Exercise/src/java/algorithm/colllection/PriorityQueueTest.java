package algorithm.colllection;

/**
 * Remove the largest or smallest item
 * <p>
 * Heap-ordered binary tree:
 * Parent's key no smaller than children's keys (heap-ordered)
 * <p>
 * Binary heap: Array representation of heap-ordered complete binary tree.
 * 1) leave a[0] empty, since it will be easy to calculate
 * 2) Largest key is a[1], the root
 * 3) Parent of node at k is at k/2.
 * 4) Children of node at k are at 2k and 2k+1.
 */
public class PriorityQueueTest<Key extends Comparable<Key>> {
    private Key[] tree;
    private int L;

    public static void main(String[] args) {
        PriorityQueueTest queue = new PriorityQueueTest();
    }

    public PriorityQueueTest() {

    }

    public void insert(Key key) {

    }

    public Key deleteMax() {
        return this.tree[1];
    }

    public boolean isEmpty() {
        return this.L == 0;
    }

    public int size() {
        return this.L;
    }

    public Key max() {
        return this.tree[1];
    }

    // move node that is larger than its parent to appropriate position
    private void swim(int k) {

    }

    // move node that is smaller than its children to appropriate position
    private void sink(int k) {

    }

    private void exchange(int i, int j) {
        Key temp = this.tree[i];
        this.tree[i] = this.tree[j];
        this.tree[j] = temp;
    }
}
