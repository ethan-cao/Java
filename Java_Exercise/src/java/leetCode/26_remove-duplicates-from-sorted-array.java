package leetCode;

import java.util.HashSet;
import java.util.Set;

class Solution26{
    public static void main(String[] args){
    	Solution26 solution  = new Solution26();

    	int[] nums = {0,0,1,1,1,2,2,3,3,4};
    	solution.removeDuplicates(nums);
	}

    public int removeDuplicates(int[] nums) {
	   	Set<Integer> uniqueValues = new HashSet<>();

		for (int i = 0; i < nums.length; ++i){
			int number = nums[i];

			if (!uniqueValues.contains(number)){
				nums[uniqueValues.size()] = number;
			}

			uniqueValues.add(number);
		}

		return uniqueValues.size();
    }
}
