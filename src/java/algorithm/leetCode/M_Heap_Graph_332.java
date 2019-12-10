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

public class M_Heap_Graph_332 {

    public static void main(String... args) {
        List<String> ticket1 = Arrays.asList(new String[]{"JFK", "KUL"});
        List<String> ticket2 = Arrays.asList(new String[]{"JFK", "NRT"});
        List<String> ticket3 = Arrays.asList(new String[]{"NRT", "JFK"});
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);

        findItinerary(tickets).forEach(System.out::println);
    }

    // Hierholzer's algorithm, Recursive
    // Time: O(ELogE), Space: O(E),  E is number of edges
    // Essentially, use each slot as node in graph, ticket as edge. Use all edge exact once (Euler tour)
    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> orderedItinerary = new LinkedList<>();

        // origin -> all destinations
        // use PriorityQueue as mini 2-heap, array-based bi
        // better than sorted array O(N)
        Map<String, PriorityQueue<String>> itineraries = new HashMap<>();

        for (List<String> ticket : tickets) {
            // !!! cannot use getOrDefault(),the default value is not associated with key in map
            // PriorityQueue<String> destinations = itineraries.getOrDefault(ticket.get(0), new PriorityQueue<>());

            PriorityQueue<String> destinations = itineraries.compute(ticket.get(0), (k, v) -> v == null ? new PriorityQueue<>() : v);
            destinations.add(ticket.get(1));
        }

        depart("JFK", itineraries, orderedItinerary);

        return orderedItinerary;
    }

    private static void depart(String origin, Map<String, PriorityQueue<String>> itineraries, List<String> orderedItinerary) {
        PriorityQueue<String> destinations = itineraries.get(origin);

        while (destinations != null && !destinations.isEmpty()) {
            String nextOrigin = destinations.remove();  // use destination with smallest lexical order as next origin
            depart(nextOrigin, itineraries, orderedItinerary);
        }

        //  building the route backwards when retreating
        orderedItinerary.add(0, origin);
    }

    // Hierholzer's algorithm, Iterative
    public static List<String> findItinerary1(List<List<String>> tickets) {
        List<String> orderedItinerary = new LinkedList<>();
        return orderedItinerary;
    }
}