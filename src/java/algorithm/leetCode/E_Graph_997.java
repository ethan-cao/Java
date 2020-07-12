package algorithm.leetCode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
In a town, there are N people labelled from 1 to N.
There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:
    1. The town judge trusts nobody.
    2. Everybody (except for the town judge) trusts the town judge.
    3. There is exactly one person that satisfies properties 1 and 2.

You are given trust, an array of pairs trust[i] = [a, b]
representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.

1 <= N <= 1000
trust.length <= 10000
trust[i] are all different
trust[i][0] != trust[i][1]
1 <= trust[i][0], trust[i][1] <= N

### Example
Input: N = 2, trust = [[1,2]]
Output: 2

Input: N = 3, trust = [[1,3],[2,3]]
Output: 3

Input: N = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1

Input: N = 3, trust = [[1,2],[2,3]]
Output: -1

Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
Output: 3

*/

public class E_Graph_997 {
    public static void main(String... args) {
//        int[][] trust = {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
//        int N = 4;  // 3

        int[][] trust = {{1,3},{2,3},{3,1}};
        int N = 3;   // -1

//        int[][] trust = {{1, 3}, {2, 3}};
//        int N = 3;  // 3

        System.out.println(findJudge1(N, trust));
    }

    // judgeLabel cannot be the 1st in trust pair
    // judgeLabel must present as 2nd in trust pair for other person
    public static int findJudge(int N, int[][] trust) {
        int judgeLabel = -1;

        Set<Integer> potentialJudge = Stream.iterate(1, n -> n + 1)
                .limit(N)
                .collect(Collectors.toSet());

        HashMap<Integer, Set<Integer>> trustRelation = new HashMap<>();
        for (int[] pair : trust) {
            Set<Integer> trustee = trustRelation.getOrDefault(pair[0], new HashSet<>());
            trustee.add(pair[1]);
            trustRelation.put(pair[0], trustee);

            potentialJudge.remove(pair[0]);
        }

        // there can be only 1 judge
        if (potentialJudge.size() != 1) {
            return judgeLabel;
        }
        judgeLabel = potentialJudge.toArray(new Integer[]{})[0];

        boolean isTrustedByAll = true;
        for (Map.Entry<Integer, Set<Integer>> entry : trustRelation.entrySet()) {
            if (!entry.getValue().contains(judgeLabel)) {
                isTrustedByAll = false;
                break;
            }
        }

        return isTrustedByAll ? judgeLabel : -1;
    }

    // Consider trust as a graph, all pairs are directed edge, from trust[0] --> trust[1]
    // the point with outDegree 0 and inDegree N - 1 is the judge
    public static int findJudge1(int N, int[][] trust) {
        // [0] : inDegree, [1] : outDegree
        int[][] degree = new int[N][2];

        for (int[] pair : trust) {
            degree[pair[0]-1][1]++;
            degree[pair[1]-1][0]++;
        }

        for (int i = 0; i < N; ++i) {
            if (degree[i][0] == N - 1 && degree[i][1] == 0){
                return i+1;
            }
        }

        return -1;
    }
}
