package algorithm.search;

/*
search for the M largest number in N numbers:
   1. create a min 2-heap of size M
   2. iterate all numbers, for each input number, replace the current min in heap min with max(min, number)
        min 2-heap sinks the node to its sorted position
   3. the M number in the min 2-heap is the largest M numbers in the N numbers
 */


public class SearchLargestNumbers {

    public static void main(String... args) {

    }

    // Time: O(N logM)
    public static int[] search(int[] nums, int M) {
        return null;
    }
}


