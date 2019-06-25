package algorithm.math;

public class Fibonacci {
    public static void main(String[] args) {
        int fibonacci = getFibonacci(10);

        System.out.println(fibonacci);
    }

    public static int getFibonacci(int n) {
        if ( n == 1|| n == 2){
            return 1;
        }

        return getFibonacci(n-1) + getFibonacci(n-2);
    }

    public static int getFibonacci1(){
      return 1;
    }
}
