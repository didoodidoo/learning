package cn.code.partice.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static volatile Thread t1 = null;
    static volatile Thread t2 = null;
    public static void main(String[] args) {

        Condition condition = new ReentrantLock().newCondition();

        t1 = new Thread(() -> {
            try {
                while (true) {
                    LockSupport.park();
                    System.out.println("t1 do sth...");
                    TimeUnit.SECONDS.sleep(5);
                    LockSupport.unpark(t2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("t2 do sth...");
                    TimeUnit.SECONDS.sleep(5);
                    LockSupport.unpark(t1);
                    LockSupport.park();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
