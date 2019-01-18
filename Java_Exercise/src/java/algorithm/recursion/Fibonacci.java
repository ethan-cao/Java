package algorithm.recursion;

public class Fibonacci {
    public static void main(String[] args) {
        int fibonacci = getFibonacci(10);

        System.out.println(fibonacci);
    }

    private static int getFibonacci(int n) {
        if ( 1 == n || 2 == n){
            return 1;
        }

        return getFibonacci(n-1) + getFibonacci(n-2);
    }
}
