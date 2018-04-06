package dateStringI18N;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

	public static void main(String[] args) {
		/*
		 * In java, for producing string "(.*)(\d+)(.*)", an extra \ is added 
		 * 
		 * the trouble is that \ is a special char in java when creating a String, regardless of regexp or not. 
		 * that means a character preceded by a backslash (\) is an escape sequence and has special meaning to the compiler. 
		 * 
		 * e.g. String s = "\t";
		 * you cannot use this for arbitrary chars though, String s = "\a"; will give you a compile-time error. 
		 * the valid chars are b, t, n, f, r, ", ' and \
		 */
		String line = "There are 100 books.";
		Pattern pattern = Pattern.compile("(.*)(\\d+)(.*)");
		Matcher m = pattern.matcher(line);

		// http://stackoverflow.com/questions/18675870/expected-outcome-in-group-capture
        // (.*) is greedy
		if ( m.find() ){ 
			//  group 0, which always represents the entire expression. 
			System.out.println("Found in group 0: " + m.group(0)); 
			
			System.out.println("Found in group 1: " + m.group(1));
			System.out.println("Found in group 2: " + m.group(2));
			System.out.println("Found in group 3: " + m.group(3));
		} else {
			System.out.println("Found nothing ");
		}

		System.out.println(System.lineSeparator());

		line = "cat cat cat cattie cat";
		// \bword\b : "whole words only"
		pattern = Pattern.compile("\\bcat\\b");
		m = pattern.matcher(line);
		int count = 0;

		/*
		 * If multiple matches can be found in the text, the find() method will find the first, 
		 * and then for each subsequent call to find() it will move to the next match
		 * for start() end(), index in string starts from 0
		 */
		while ( m.find() ){
			count++;
		    System.out.println("Match number " + count);
	        System.out.println("group(): "+ m.group());
	        System.out.println("start(): "+ m.start());
	        System.out.println("end(): " + m.end());			
		}
			
		wordBoundaryTest(args);
	}
	
	static void wordBoundaryTest(String[] args){
		/*
		 * in  unix/linux:
		 * use "\B" and "^23 *$76 bc" in bash java command to output 0 2 4 8
		 * since $7 is the positional parameter and it is taken to be a variable without value assigned to it
		 * "^23 *$76 bc" is translated into "^23 *6 bc" in bash before it is passed to java program
		 *
		 * in windows:
		 * use "\B"  "^23 *6 bc" in java command to output 0 2 4 8
		 */
		
		Pattern p = Pattern.compile(args[0]);
		Matcher m = p.matcher(args[1]);
		while(m.find()) {
			System.out.print(m.start() + " ");
		}
		System.out.println("");
	}
	

}
