package algorithm.leetCode;

/*
Suppose you have a random list of people standing in a queue. The number of people is less than 1,100.

Each person is described by a pair of integers (h, k), where h is the height of the person
k is the number of people in front of this person who have a height >= h

Write an algorithm to reconstruct the queue. So that k is correct given the person's position in the queue.

### Example
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
->
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

*/

import java.util.*;

public class M_Greedy_Array_406 {

    public static void main(String... args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] people1 = {{9, 0}, {7, 0}, {1, 9}, {3, 0}, {2, 7}, {5, 3}, {6, 0}, {3, 4}, {6, 2}, {5, 2}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

    // Brute Force, TLE
    // Time: O(N^3),
    public static int[][] reconstructQueue0(int[][] people) {
        if (people.length < 2) {
            return people;
        }

        for (int i = 0; i < people.length; ) {
            boolean swapped = false;

            int[] person = people[i];
            int height = person[0];
            int expectedTallerPersonCount = person[1];
            int actualTallerPersonCount = getActualTallerPersonCount(people, i);

            int current = i;

            while (actualTallerPersonCount < expectedTallerPersonCount && current + 1 < people.length) {
                swap(people, current, current + 1);
                current++;
                actualTallerPersonCount = getActualTallerPersonCount(people, current);
                swapped = true;
            }

            while (actualTallerPersonCount > expectedTallerPersonCount && current - 1 >= 0) {
                swap(people, current, current - 1);
                current--;
                actualTallerPersonCount = getActualTallerPersonCount(people, current);
                swapped = true;

                i = current;  // !!! if move towards left, it invalidate structured people
            }

            if (!swapped) {
                i++;
            }
        }

        return people;
    }

    private static int getActualTallerPersonCount(int[][] queue, int idx) {
        int count = 0;

        for (int i = 0; i < idx; ++i) {
            if (queue[i][0] >= queue[idx][0]) {
                count++;
            }
        }

        return count;
    }

    private static void swap(int[][] queue, int idx1, int idx2) {
        int[] temp = queue[idx1];
        queue[idx1] = queue[idx2];
        queue[idx2] = temp;
    }

    // Time: same as Arrays.sort()
    public static int[][] reconstructQueue(int[][] people) {
        if (people.length < 2) {
            return people;
        }

        // sort people from tall to short, for same height, people with smaller k comes first
        // since the tallest person's k is irrelevant to anyone, we will position them first
        // implement quick sorting to sort can improve performance
        Arrays.sort(people, (person1, person2) -> person1[0] == person2[0] ? person1[1] - person2[1] : person2[0] - person1[0]);

        // ArrayList is faster than LinkedList in this case, maybe just more iteration is used
        // Normally, one would just use LinkedList
        List<int[]> queue = new ArrayList<>();

        // start assigning position from the tallest person, since the tallest person's position is irrelevant to anyone
        // so just need to position the person with tallest height according to their k in ascending order
        // then start from the next tallest person, again and again, until the shortest one
        for (int[] p : people) {
            // k is the exactly position the person stands
            // shift person currently at position k (if any) and any subsequent person to the right
            queue.add(p[1], p);
        }

        return queue.toArray(new int[0][0]);
    }

}