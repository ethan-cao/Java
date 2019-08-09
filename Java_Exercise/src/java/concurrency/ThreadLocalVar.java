package concurrency;

public class ThreadLocalVar {

    private ThreadLocalVar(){
    }

    private static final ThreadLocal<ThreadLocalVar> threadLocalVar = new ThreadLocal<ThreadLocalVar>(){
        @Override
        protected ThreadLocalVar initialValue(){
            return new ThreadLocalVar();
        }
    };

    // return thread local variable
    public static ThreadLocalVar getInstance() {
        return threadLocalVar.get();
    }
}
