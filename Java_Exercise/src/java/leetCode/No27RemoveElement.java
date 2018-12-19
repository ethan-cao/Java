package leetCode;


public class No27RemoveElement {
    public static void main(String[] args) {
        No27RemoveElement s = new No27RemoveElement();
        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int newLength = s.removeElement(nums, 2);
        System.out.println("new length : " + newLength);
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int pointer = 0;

        for (int i = 0; i < nums.length; ++i) {

            int currentVal = nums[i];

            if (currentVal != val) {
                nums[pointer++] = currentVal;
            }
        }

        return pointer;
    }
}
