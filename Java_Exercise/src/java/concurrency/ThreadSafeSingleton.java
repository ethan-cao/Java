package concurrency;

public class ThreadSafeSingleton {

    private static class InstanceHolder {
        private static final ThreadSafeSingleton instance = new ThreadSafeSingleton();
    }

    private ThreadSafeSingleton(){
    }

    // return a thread safe singleton instance
    public static ThreadSafeSingleton getInstance(){
        return InstanceHolder.instance;
    }

}
