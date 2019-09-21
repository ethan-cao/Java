package algorithm.dataStructure;

/**
 * Use integers to represent vertex
 */

public class Graph {

    public static void main(String... args) {

    }

    static class Bag<K> {
        int value;
        Bag next;
    }

    private int numberOfVertices;

    // Adjacency-list graph representation
    // adjacentVertices[i] :  collection of vertices that are connected to vertex i
    private Bag<Integer>[] adjacentVertices;

    /**
     * add vertex
     */
    public void addVertex(int i) {
        // if not existed
        this.numberOfVertices++;
    }

    /**
     * add edge between vertex v and w
     */
    public void addEdge(int v, int w) {

    }

    /**
     * get adjacent vertices of vertex v
     */
    Iterable<Integer> adj(int v) {
        return null;
    }

    int numberOfVertices() {
        return this.numberOfVertices;
    }

    int numberOfEdges() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
