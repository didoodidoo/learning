package cn.code.partice.util.thread;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public  class ThreadPoolUtil {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    public static void execute(Runnable task) {
        fixedThreadPool.submit(task);
    }


}
