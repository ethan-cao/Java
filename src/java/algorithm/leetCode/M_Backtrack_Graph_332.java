package algorithm.leetCode;

/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
reconstruct the itinerary in order.
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

If there are multiple valid itineraries,
you should return the itinerary that has the smallest lexical order when read as a single string.
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].

All airports are represented by three capital letters.
You may assume all tickets form at least one valid itinerary.

### Example
[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
->
["JFK", "MUC", "LHR", "SFO", "SJC"]


[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
->
["JFK","ATL","JFK","SFO","ATL","SFO"]
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
But it is larger in lexical order.

### Review:

 */

import java.util.*;

public class M_Backtrack_Graph_332 {

    public static void main(String... args) {
        List<String> ticket1 = Arrays.asList(new String[]{"JFK", "KUL"});
        List<String> ticket2 = Arrays.asList(new String[]{"JFK", "NRT"});
        List<String> ticket3 = Arrays.asList(new String[]{"NRT", "JFK"});
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);

        findItinerary1(tickets).forEach(System.out::println);   // "JFK", "NRT", "JFK", "KUL"
    }

    // this problem asks to construct an itinerary using all all tickets once and start fromm JFK
    // Essentially, use city as node, ticket as edge, in a directed graph
    // use all tickets means using all edge exact once, namely Eulerian path
    // reconstruct the itinerary means find a Eulerian path starting from JFK
    // The problem implicitly acknowledge there is a Eulerian path, no need to check if it exists


    // Recursive DFS
    // Time: O(ELogE), Space: O(E),  E is number of edges
    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> orderedItinerary = new LinkedList<>();

        // build a graph: origin -> all destinations
        // all destinations should be a sorted sequence with lexical order
        // use sorted arry is O(N), use 2-heap is O(logN)
        // so use PriorityQueue (mini 2-heap, array-based binary heap) to build the sorted sequence
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        // O(ElogE)
        for (List<String> ticket : tickets) {
            // !!! cannot use getOrDefault(),the default value is not associated with key in map
            // PriorityQueue<String> destinations = graph.getOrDefault(ticket.get(0), new PriorityQueue<>());

            // O(logE), if use sorted array, it is O(E)
            PriorityQueue<String> destinations = graph.compute(ticket.get(0), (k, v) -> v == null ? new PriorityQueue<>() : v);
            destinations.add(ticket.get(1));
        }

        // construct an Eulerian path starting from JFK
        // Hierholzer's algorithm, O(E),  E is number of edges
        // pick JFK, the vertex with 1 additional outDegree, as start
        depart(graph, "JFK", orderedItinerary);

        return orderedItinerary;
    }

    private static void depart(Map<String, PriorityQueue<String>> graph, String origin, List<String> EulerianPath) {
        PriorityQueue<String> destinations = graph.get(origin);

        // DFS
        // follows an arbitrary unvisited edge (lexical order) to a neighbour til the end
        while (destinations != null && !destinations.isEmpty()) {
            String nextOrigin = destinations.remove();  // min heap's remove() removes the smallest lexical one
            depart(graph, nextOrigin, EulerianPath);
        }

        // build the Eulerian path backwards when reach the end
        EulerianPath.add(0, origin);
    }

    // Iterative DFS
    public static List<String> findItinerary1(List<List<String>> tickets) {
        List<String> orderedItinerary = new LinkedList<>();

        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            // use stack to simulate callstack
            PriorityQueue<String> destinations = graph.compute(ticket.get(0), (k, v) -> v == null ? new PriorityQueue<>() : v);
            destinations.add(ticket.get(1));
        }

        // use stack to simulate callstack
        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");

        while (!stack.isEmpty()) {
            String origin = stack.peekFirst();
            PriorityQueue<String> destinations = graph.get(origin);

            while (destinations != null && !destinations.isEmpty()) {
                String newOrigin = destinations.remove();
                stack.push(newOrigin);
                destinations = graph.get(newOrigin);
            }

            orderedItinerary.add(0, stack.pop());
        }

        return orderedItinerary;
    }
}