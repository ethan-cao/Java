package concurrency;

import java.util.concurrent.locks.*;

public class ReentrantLockTest {
    public static void main(String[] args) {
        LockExample.lockusingReentrantLock();
    }
}

class LockExample {

    // lockUsingSyc() is equivalent to lockusingReentrantLock()
    public static void lockUsingiSyn() {
        Object obj = new Object();
        synchronized (obj) {
            // do the work 
        }
    }

    public static void lockusingReentrantLock() {
        Lock lock = new ReentrantLock();
        lock.lock(); // blocks until acquired the lock
        // comparing with synchronized, instead of holding lock of a particular object
        // we lock and unlock a specific lock

        try {
            // do the work
        } finally {
            lock.unlock();
        }
    }

    // attempLock() is a better version of lockusingReentrantLock()
    // tryLock() give the ability to attempt (and fail) to acquire a lock.
    // that means, if fail to get the lock, we can still working on something else
    // instead of blocking there, waiting time
    public void attemptLock() {
        Lock lock = new ReentrantLock();
        boolean locked = lock.tryLock();
        // true: can acquire the lock
        // false : cannot acquire the lock
        // variation lock.tryLock(3, TimeUnit.SECONDS);

        if (locked) {
            try {
                // do the work
            } finally {
                lock.unlock();
            }
        }
    }


    Lock lock = new ReentrantLock();
    Condition blockingPoolA = lock.newCondition();

    // conditionTest1() is like Thread.sleep(), conditionTest2() is like notifyAll()
    public void conditionTest1() {
        lock.lock(); // lock() is need before using await()

        try {
            blockingPoolA.await();
            // let the current thread wait, so another thread can do its work
            // in this case, conditionTest2 will start working
            // When invoke await, Lock associated with the Condition is released.

            // Before await() returns, the lock will be re-acquired.

            // do the work
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    // This should run in another thread from condifitonTest1
    public void conditionTest2() {
        lock.lock();

        try {
            // do the work

            // signalAll() causes all threads awaiting on the same Condition to wake up.
            // Each awaken thread will re-acquire the Lock before continuing.
            // in this case, conditionTest1 will resume working
            blockingPoolA.signalAll();
            // signal() method to wake up a single awaiting thread.
        } finally {
            lock.unlock(); // now the awoken thread can run
        }
    }
}

/**
 * As an example, suppose we have a bounded buffer which supports put and take methods.
 * If a take is attempted on an empty buffer, then the thread will block until an item becomes available;
 * if a put is attempted on a full buffer, then the thread will block until a space becomes available.
 * We would like to keep waiting put threads and take threads in separate wait-sets
 * so that we can use the optimization of only notifying a single thread at a time when items or
 * spaces become available in the buffer. This can be achieved using two Condition instances.
 */

class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}