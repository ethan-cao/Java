package designPattern;

import java.io.Serializable;

/**
 * Builder Pattern
 *
 * Ensure a class only has one instance, and provide a global point of access to it
 */

class SingletonPattern {
    public static void main(String[] args) {
        Singleton s1 = Singleton.INSTANCE;
        Singleton s2 = Singleton.INSTANCE;
        System.out.println(s1 == s2); // true

        Singleton1 ss1 = Singleton1.getInstance();
        Singleton1 ss2 = Singleton1.getInstance();
        System.out.println(ss1 == ss2); // true
    }
}

// serializable, thread-safe, singleton
// the best way if you don't need inheritance
enum Singleton {
    INSTANCE;
}
// Which internally will be treated like
//public class SingletonClass {
//    private static final Foo INSTANCE = new SingletonClass();
//}


// serializable, thread-safe, with parent class,
// https://stackoverflow.com/questions/70689/what-is-an-efficient-way-to-implement-a-singleton-pattern-in-java/
// ??? Not sure why need final
final class Singleton1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Singleton1() {
        if (Loader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    private static class Loader {
        private static final Singleton1 INSTANCE = new Singleton1();
    }

    // lazy loading, when getInstance() is invoked, Loader is loaded and the instance is instantiated
    public static Singleton1 getInstance() {
        return Loader.INSTANCE;
    }

    // this is used to make sure the only instance will be returned,
    // even when the object was serialized in a previous run of your program.
    private Singleton1 readResolve() {
        return Loader.INSTANCE;
    }

}