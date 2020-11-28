package cn.code.partice.netty.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkGroupThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(WorkGroupThread.class);
    //用来工作的线程池
    ExecutorService pool;

    LinkedBlockingQueue<SelectionKey> queue;

    public WorkGroupThread(int threadCount, LinkedBlockingQueue<SelectionKey> queue) {
        pool = Executors.newFixedThreadPool(threadCount);
        this.queue = queue;
    }


    @Override
    public void run() {
        while (true) {
//            拿不到key会阻塞的
            logger.info("wait for key from queue");
            SelectionKey key = null;
            try {
                key = queue.take();
            } catch (InterruptedException e) {
                logger.info("线程异常", e);
            }
            logger.info("start thread to handle key:"+queue.size());
            pool.submit(new WorkThread(key));
        }
    }
}
