package designPattern;

public class SingletonTest {
    public static void main(String[] args) {
        SingletonClass s1 = SingletonClass.getInstance();
        SingletonClass s2 = SingletonClass.getInstance();
        System.out.println(s1.equals(s2));


        SingletonClass1 ss1 = SingletonClass1.INSTANCE;
        SingletonClass1 ss2 = SingletonClass1.INSTANCE;
        System.out.println(ss1.equals(ss2));
    }
}

// non thread-safe
class SingletonClass {
    private static SingletonClass INSTANCE = new SingletonClass();

    // make it impossible to create from outside
    private SingletonClass() {
    }

    // the only way to get instance
    public synchronized static SingletonClass getInstance() {
        // lazy initialization
        if (INSTANCE == null) {
            INSTANCE = new SingletonClass();
        }
        return INSTANCE;
    }

}


// This is thread safe, but it cannot have parent class
// the best way if you don't need inheritance
enum SingletonClass1 {
    INSTANCE;
}

// This is thread safe to create singleton instance and it can have parent class
// https://stackoverflow.com/questions/16106260/thread-safe-singleton-class
class SingletonClass2 {
    private SingletonClass2() {
    }

    private static class InstanceHolder {
        private static final SingletonClass2 INSTANCE = new SingletonClass2();
    }

    // when first call getInstance(), InstanceHolder is loaded and the instance is instantiated
    public static SingletonClass2 getInstance() {
        return InstanceHolder.INSTANCE;
    }
}