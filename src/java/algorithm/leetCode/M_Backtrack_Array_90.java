package algorithm.leetCode;

/*
Given a collection of integers that might contain duplicates, nums,
return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets

### Example
[1,2,2] -> [  [2],  [1],  [1,2,2],  [2,2], [1,2],  [] ]

*/

import java.util.*;

public class M_Backtrack_Array_90 {

    public static void main(String... args) {
        int[] nums = { 4, 4, 4, 1, 4 };

        List<List<Integer>> subsets = subsetsWithDup1(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
        //[[],[1],[1,4],[1,4,4],[1,4,4,4],[1,4,4,4,4],[4],[4,4],[4,4,4],[4,4,4,4]]

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Backtrack
    // 1ms
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> tracker = new ArrayList<>();

        Arrays.sort(nums);

        collect(subsets, tracker, nums, 0);

        return subsets;
    }

    private void collect(List<List<Integer>> subsets, List<Integer> tracker, int[] nums, int idx) {
        subsets.add(new ArrayList<>(tracker));

        for (int i = idx; i < nums.length; ++i) {
            // skip duplicate, require array to be sorted
            // !!! i > start, only avoid to-be added num are not the same
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }

            tracker.add(nums[i]);

            collect(subsets, tracker, nums, i + 1);

            tracker.remove(tracker.size() - 1);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Iterative, Time and Space wise, same as backtrack
    public static List<List<Integer>> subsetsWithDup1(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> subsets = new ArrayList<>();
        int previousSize = subsets.size();

        subsets.add(new ArrayList<>());
        int size = subsets.size();

        for (int i = 0; i < nums.length; ++i) {
            // if identical item, start appending from previousEnd to avoid generating identical subset
            int start = (i > 0 && nums[i] == nums[i - 1]) ? previousSize : 0;

            for (int j = start; j < size; ++j) {
                List<Integer> subset = new ArrayList<>(subsets.get(j));
                subset.add(nums[i]);
                subsets.add(subset);
            }

            previousSize = size;
            size = subsets.size();
        }

        return subsets;
    }

}