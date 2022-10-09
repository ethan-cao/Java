package algorithm.dataStructure;

class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new QueueByLinkedList<>();

        queue.enqueue(1);     // 1
        queue.enqueue(2);     // 2, 1
        queue.enqueue(3);     // 3, 2, 1
        queue.dequeue();         // 3, 2
        queue.enqueue(4);     // 4, 3, 2
        queue.dequeue();         // 4, 3

        queue.print();
    }
}

public interface Queue<T> {
    void enqueue(T t);
    T dequeue();
    boolean isEmpty();
    int size();
    void print();
}

class QueueByLinkedList<T> implements Queue<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        T t;
        Node next;

        Node(T t){
            this.t = t;
        }
    }

    @Override
    public void enqueue(T t) {
        Node newNode = new Node(t);
        Node oldLast = this.last;

        this.last = newNode;

        if (this.isEmpty()){
            this.first = this.last;
        } else {
            oldLast.next = newNode;
        }

        this.size++;
    }

    @Override
    public T dequeue() {
        Node firstNode = this.first;

        this.first = firstNode.next;
        firstNode.next = null;

        if (this.isEmpty()){
            this.last = null;
        }

        this.size--;

        return firstNode.t;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void print() {
        Node node = this.first;
        while (node!=null){
            System.out.print(node.t + " ");
            node = node.next;
        }
    }
}
