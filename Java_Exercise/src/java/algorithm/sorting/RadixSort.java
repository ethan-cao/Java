package algorithm.sorting;

/**
 * Radix sort handles sorting by implementing counting sort (or bucket sort) on one digit at a time.
 *  It generally works best for a range of smallish numbers,
 *  the very first sorting algorithm to be created
 *
 *  complexity is O(wn), n number of elements, w number of digit of the largest element
 *
 *  Rarely use since time complexity, LSD is more often than MSD
 *
 *  1. most significant digit (MSD) radix sort :  from the greatest digit, and moving towards the least significant digit
 *  2. least significant digit (LSD) radix sort : the smallest digit first, moving towards the greater
 */

public class RadixSort {
    public static void main(String[] args){
        int[] data = {2, 4, 5, 3, 1};
        int l = data.length;

    }
}
