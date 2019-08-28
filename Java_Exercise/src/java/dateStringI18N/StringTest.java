package dateStringI18N;

public class StringTest {

	public static void main(String[] args) {
		String s1 = "string";
		String s2 = " A BBBbB ";
		String s3 = "string";

		char data[] = { 'a', 'b', 'c' };
		String str = new String(data); // is equivalent to String str = "abc";

		String sss = new String("test");

		// String is immutable. the return is new result
		System.out.println("length: " + s1.length());
		System.out.println("substring: " + s1.substring(2, 4));   // 2: The beginning index, inclusive.  4:The ending index, exclusive. 
		System.out.println("substring: " + s1.substring(2));   // s 小写
		System.out.println("charAt: " + s1.charAt(2));
		System.out.println("replace: " + s2.replace("B", "C"));  //replace all 
		System.out.println("concat: "+ s1.concat(s2));
		System.out.println("trim: " + s2.trim()); // only remove blank in the beginning and the end
		System.out.println("compareTo: " + "computer".compareTo("comparison"));  // ASCII Code, u(117) - a(97) = 20
		System.out.println("contains: " + s1.contains("ir"));
		System.out.println("toLowerCase: " + s2.toLowerCase());
		System.out.println("toUpperCase: " + s1.toUpperCase());
		System.out.println("endsWith: " + s1.endsWith("ing")); 

		System.out.println( System.lineSeparator() );

		String st1 = "abc";
        String st2 = st1;
        st1 += "d";
        System.out.println( st1 + " " + st2 + " " + (st1 == st2) );  // different reference

		// method can be chained
		System.out.println("chained: " + s1.replace("s", "e").replace("t", "a")); 
		
		char[] arr = {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
		System.out.println("copyValueOf: " + String.copyValueOf(arr)); // static method
		System.out.println("copyValueOf: " + String.copyValueOf(arr, 3, 7)); // copyValueOf(char[] data, int offset, int count)
		
		// compare String
		System.out.println("test".equalsIgnoreCase("teSt"));  // true
		System.out.println(s1.equals(s3));
		System.out.println("test".equals("teSt"));

		// OCP
        // 6, only empty string before delimiter is counted
		String in = "1 a2 b 3 c4d 5e1";
        String[] chunks = in.split("\\d");
        System.out.println("count " + chunks.length);

		// this will cause compiler error, + does not support StringBuffer and int
//		StringBuffer sb = new StringBuffer("3");
//		System.out.print(5 + 4 + sb + 2 + 1);
	}

	void performance(){
		String s = "{a:"+ ", b:" + ", c: " + "}";
		// compiler will turn this into StringBuilder, which is faster
		new StringBuilder().append("{a:").append(", b:").append(", c:").append("}");

		// if concatenate in a loop - compiler usually can't substitute StringBuilder by itself
	}
}