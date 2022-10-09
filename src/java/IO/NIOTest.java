package IO;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOTest {

	public static void main(String[] args) {
		Path path = Paths.get("C:\\education\\institute\\student\\report.txt");

		System.out.printf("get.Name(0): %s \n", path.getName(0));
		System.out.printf("subpath(0, 2): %s \n", path.subpath(0, 2));
		
		System.err.println();

	}

}

//class App {
//	public static void main(String[] args) {
//		Path path = Paths.get("C:\\education\\institute\\student\\report.txt");
//
//		System.out.printf("get.Name(0): %s \n", path.getName(0));
//		System.out.printf("subpath(0, 2): %s \n", path.subpath(0, 2));
//	}
//}