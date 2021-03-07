package cn.code.zeus.juc.lock;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;

public class LockTest {


    Object lockObject = new Object();

    public void reentrantLockTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        lock.newCondition().signal();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " has got lock sleep 5s");
                try {
                    lock.tryLock(10, TimeUnit.SECONDS);//可重入
                    TimeUnit.SECONDS.sleep(5);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放 lock");
                    lock.unlock();
                }
            }).start();
        }
    }

    public void lockSupportTest() {

    }

    public void readWriteLockTest() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        //共享锁和排它锁 读锁之间不互斥 写锁互斥
        readLock.lock();
        readLock.unlock();
        writeLock.lock();
        writeLock.unlock();

        StampedLock stampedLock = new StampedLock();
        Lock rLock = stampedLock.asReadWriteLock().readLock();
        Lock wLock = stampedLock.asReadWriteLock().writeLock();
        rLock.lock();
        rLock.unlock();
        wLock.lock();
        wLock.unlock();

    }


    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
    }

}
