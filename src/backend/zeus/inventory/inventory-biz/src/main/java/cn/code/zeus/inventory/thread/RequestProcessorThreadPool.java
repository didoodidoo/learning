package cn.code.zeus.inventory.thread;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池 要求单例 从队列中不断的拿消息出来消费
 */
@Component
public class RequestProcessorThreadPool {

    private ExecutorService pool = Executors.newFixedThreadPool(16);

    public void execute(Callable<Boolean> task) {
        pool.submit(task);
    }
}
