package leetCode;

public class No53MaximumSubarray {

    public static void main(String[] args){

    }

    /**
     *  Dynamic programming
     */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        // maxSubArray(A, i) = Math.max(maxSubArray(A, i-1), 0) + A[i];
        // i could be 1, 2, .. nums.length -1

        int maxSum = nums[0];
        int localSum = nums[0];

        for (int i = 1; i < nums.length; ++i){
            localSum = Math.max(localSum, 0) + nums[i];
            maxSum = Math.max(localSum, maxSum) ;
        }

        return maxSum;
    }

    /**
     *  Divide and conquer
     */
    public int maxSubArray2(int[] nums) {
        int maxSum = 0;

        return maxSum;
    }
}
