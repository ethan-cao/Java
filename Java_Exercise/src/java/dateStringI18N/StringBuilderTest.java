package dateStringI18N;

public class StringBuilderTest {

	public static void main(String[] args) {
		StringBuilder sb1 = new StringBuilder("stringBuilder1");
		StringBuilder sb2 = new StringBuilder("stringBuilder2");
		String s = "s";

		// StingBuilder is mutable
		System.out.println("length: " + sb1.length() );
		System.out.println("append: " + sb1.append(sb2));
		System.out.println("append: " + sb1.append(s));  // can append string 
		System.out.println("append: " + sb1.append("ok"));  // can append string literal
		// sb1.append("My dog" + s);  // this also works
		
		System.out.println("insert: " + sb1.insert(0, "asd"));
		System.out.println("delete: " + sb1.delete(2, 4));  // 2: The beginning index, inclusive.  4:The ending index, exclusive. 
		System.out.println("reverse: " + sb1.reverse());
		System.out.println("toString: " + sb1.toString());  // convert stringBuilder to String
		
		sb1.delete(0, sb1.length()); // empty a stringBuilder
		System.out.println("empty: " + sb1);

		
		// final stringBuilder just means the reference sb4 cannot be reassigned. the value can change.
		final StringBuilder sb4 = new StringBuilder("stringBuilder3");
		sb4.append("test");
		System.out.println("final sb4 : " + sb4);
		
		// method can be chained
		StringBuilder sb = new StringBuilder("abc");
		sb.append("def").reverse().insert(3, "---");
		
		
		String ss1 = "asd";
		StringBuilder ss2 = new StringBuilder("asd");
		// without toStirng, the result is n
//		if (ss1.equals(ss2.toString())) {
		if (ss1.equals(ss2)) {
			System.out.println('y');
		} else {
			System.out.println('n');
		}

	}

}
