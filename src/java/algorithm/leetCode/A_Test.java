class A_Test {

    public static void main(String[] args) {
        System.out.println("Debugging...");
        canJump(new int[] { 2, 0, 0 });
    }

    public static boolean canJump(int[] nums) {

        boolean jump[] = new boolean[nums.length];

        jump[0] = true;

        for (int idx1 = 1; idx1 < nums.length; ++idx1) {
            for (int idx2 = 0; idx2 < idx1; ++idx2) {
                if (jump[idx2]) {
                    jump[idx1] = nums[idx2] >= idx1 - idx2;
                }

                if (jump[idx1]) {
                    break;
                }
            }
        }

        return jump[nums.length - 1];
    }
}