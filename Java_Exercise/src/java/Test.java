import java.util.*;

public class Test {

    public static void main(String... args) {
        int[] data = {2, 2222, 2, 23, 5, 13};

        System.out.println(gcd(240, 36));
        System.out.println(lcm(240, 36));

    }

    static int gcd(int a, int b){
        return b > 0 ? gcd(b, a%b) : b;
    }

    static int lcm(int a, int b){
        return a * b / gcd(a, b);
    }
}