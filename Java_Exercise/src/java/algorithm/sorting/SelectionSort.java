package algorithm.sorting;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int[] A = {2,4,5,3,1};
		int l = A.length;

		for (int i = 0; i < l-1; ++i ){
			int minPos = i;
			
			// Find the location of the minimal element
			for (int j = i + 1; j < l; ++j){
				if ( A[j] < A[minPos]){
					minPos = j;
				}
			}
			
			if (minPos != i){
				int temp = A[i];
				A[i] = A[minPos];	
				A[minPos] = temp;
			}
		}

		System.out.println(Arrays.toString(A));
	}
}
