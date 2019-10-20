package IO;

import java.io.Console;

public class ConsoleTest {

    public static void main(String[] args){
       Console c = System.console();
       System.out.println("Please enter your name: ");
       String name = c.readLine();
       System.out.println("Welcome " + name);
    }

}