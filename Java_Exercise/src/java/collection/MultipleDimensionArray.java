package collection;

public class MultipleDimensionArray {

	public static void main(String[] args) {
        int[][] array2d = new int[2][3];
		
		System.out.println(array2d.length);  // 2
		System.out.println(array2d[0].length);  // 3
		
		for (int x = 0; x < array2d.length; x++) {
			for (int y = 0; y < array2d[0].length; y++) {
				System.out.println(" x = " + x);
				System.out.println(" y = " + y);
			}
		}

		for (int x = 0; x < array2d.length; x++) {
			for (int y = 0; y < array2d[0].length; y++) {
				System.out.println(" x = " + x);
				System.out.println(" y = " + y);
			}
		}
		
	}

}
