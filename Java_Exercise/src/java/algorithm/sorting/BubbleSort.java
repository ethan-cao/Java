package algorithm.sorting;

import java.util.Arrays;

public class BubbleSort {
	public  static void main(String[] args) {
		int[] A = {2,4,5,3,1};
		int l = A.length;

		for (int i = 1; i < l; ++i){
			for (int j = 0; j < l - i; ++j){
				if (A[j] > A[j+1]){
					A[j+1] += A[j];
					A[j] = A[j+1] - A[j];
					A[j+1] = A[j+1] - A[j];
				}
			}
		}

		// for (int i = 1; i < A.length-2; ++i){
		// 	if (A[i] > A[i+1]){
		// 		A[i+1] += A[i];
		// 		A[i] = A[i+1] - A[i];
		// 		A[i+1] = A[i+1] - A[i];
		// 	}
		// }
		//
		// for (int i = 1; i < A.length-3; ++i){
		// 	if (A[i] > A[i+1]){
		// 		A[i+1] += A[i];
		// 		A[i] = A[i+1] - A[i];
		// 		A[i+1] = A[i+1] - A[i];
		// 	}
		// }
		//
		
		System.out.println( Arrays.toString(A) );
	}
}
