package algorithm.sort;

import java.util.Arrays;

/**
 * Bucket sort or Bin sort
 *
 * Compared with counting sort, bucket sort succeeds by constructing a much smaller set of k values
 *
 * Its purpose is to improve counting sort
 *
 * It is similar to Radix sort, but after 1st partition, it uses other sorting mechanism to sort each bucket
 *
 * LeetCode: 740, 198
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] data = {10, 10, 5, 11, 13, 1};

        sort(data, 13); // 13 is range, the max value

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data, int range) {
        int bucketSize = getBucketSize(data, range); // number of bucket
        int[][] bucket = new int[bucketSize][data.length];
        int[] counter = new int[bucketSize]; // counter[i] is the number of elements in bucket[i]

        // partition elements to each bucket
        for (int i = 0; i < data.length; ++i) {
            int bucketID = data[i] / (range + 1) * bucketSize;
            bucket[bucketID][counter[bucketID]] = data[i];
            counter[bucketID]++;
        }

        // sort each bucket
        for (int i = 0; i < bucketSize; ++i) {
            InsertionSort.sort(bucket[i]);
        }

        // collect result
        int idx = 0;
        for (int i = 0; i < bucketSize; ++i) {
            for (int j = 0; j < counter[i]; ++j) {
                data[idx] = bucket[i][j];
                idx++;
            }
        }
    }

    // the key for bucket sort is how to getPartitionKey data to buckets
    // this is just one example
    // kind of similar to hash()
    private static int getBucketSize(int[] data, int range) {
        return range / data.length + 1;
    }

}
