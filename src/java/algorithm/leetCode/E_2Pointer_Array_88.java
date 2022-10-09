package algorithm.leetCode;

import java.util.Arrays;

public class E_2Pointer_Array_88 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

//        int[] nums1 = {2, 0};
//        int m = 1;
//        int[] nums2 = {1};
//        int n = 1;

        merge2(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }

    // mergeSort idea, using merge
    static void merge1(int[] nums1, int m, int[] nums2, int n) {
        if (0 == n) {
            return;
        }

        int[] aux = new int[m];
        System.arraycopy(nums1, 0, aux, 0, m);
        int idx1 = 0;   // starting index for sorted array[0 - (m-1)], aux
        int idx2 = 0;   // starting index for sorted array[0 - (n-1)], nums2

        for (int i = 0; i < m + n; ++i) {
            if (idx1 >= m) {
                nums1[i] = nums2[idx2++];
            } else if (idx2 >= n) {
                nums1[i] = aux[idx1++];
            } else if (aux[idx1] <= nums2[idx2]) {
                nums1[i] = aux[idx1++];
            } else {
                nums1[i] = nums2[idx2++];
            }
        }

    }

    static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int idx1 = m - 1;
        int idx2 = n - 1;
        int idx3 = m + n - 1;

        // sort array in reversed order
        while (idx1 >= 0 && idx2 >= 0) {
            nums1[idx3--] = nums1[idx1] > nums2[idx2] ? nums1[idx1--] : nums2[idx2--];
        }

        while (idx2 >= 0){
            nums1[idx3--] = nums2[idx2--];
        }
    }
}
