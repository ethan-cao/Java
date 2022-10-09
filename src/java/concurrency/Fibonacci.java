package concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

    public static void main(String... args){
        Integer i = new Fibonacci(20).compute();
        System.out.println(i);
    }

    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    protected Integer compute() {
        if (n <= 1)
            return n;

        Fibonacci f1 = new Fibonacci(n - 1);
        Fibonacci f2 = new Fibonacci(n - 2);

        f1.fork();

//        return f2.logStar() + f1.join(); // Line X
        return f1.join() + f2.compute() ; // Line X
    }

    public static long fibonacci(int x) {
        Map<Integer,Long> memo = new HashMap<>();
        return memo.computeIfAbsent(x, n -> fibonacci(n-2) + fibonacci(n-1));
    }
}
