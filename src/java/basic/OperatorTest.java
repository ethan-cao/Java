package basic;

public class OperatorTest {

	public static void main(String[] args) {

		for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++){
			System.out.println(b);
			if (b == 0x90){
				System.out.println("ok");
			}
		}
		
		//This is happening because * has a higher priority than +
		System.out.println("Result: " + 2 + 3 * 5);

		int j=0;
	    for(int i=0;i<100;i++){ 
	    	j = j++;  // the result is 0
//          j = ++j; // the result is 100
	    }
		System.out.println(j); //prints 0
		
		int s = 10;
		
//		s += s++;
		s += ++s;
		System.out.println("s : " + s);
		
		int a = 0;
		a++; 
		System.out.println("a: "+ a++); 
		System.out.println("a: "+ a);
		
		//The arguments to each operator are evaluated left-to-right. 
		//I.e., the a in front of the [...] is evaluated before its contents, at which point it still refers to the first array.
		int[] arr = { 1, 2, 3, 4 };   
		int[] b = { 2, 3, 1, 0 };   
		System.out.println( arr [ (arr = b)[3] ] );  // output 1
		
	}
	
	
	public static int example1(){
		int i = 2;
		int j = ++i + i++;
		
		System.out.println(j);

		return  j;  // 3 + 3 = 6
	}
	
	public static int example2(){
		int i = 2;
		int j = i++ + ++i;

		System.out.println(j);

		return  j;  // 2 + 4 = 6
	}

}

