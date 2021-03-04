package cn.code.zeus.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

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
                    lock.tryLock(10,TimeUnit.SECONDS);//可重入
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

    public void lockSupportTest(){
    }


    public static void main(String[] args) throws InterruptedException {
        new LockTest().reentrantLockTest();
    }

}
