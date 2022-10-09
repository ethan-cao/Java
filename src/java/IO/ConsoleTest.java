package IO;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleTest {

    public static void main(String[] args) {
        Console c = System.console();
        System.out.println("Please enter your name: ");
        String name = c.readLine();
        System.out.println("Welcome " + name);

        Scanner scanner = new Scanner(System.in);
        String tokens[] = scanner.nextLine().split(" ");
        System.out.println(Arrays.asList(tokens)); //
        scanner.close();
    }

}