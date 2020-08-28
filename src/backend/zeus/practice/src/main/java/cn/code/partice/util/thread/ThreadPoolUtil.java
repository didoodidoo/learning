package cn.code.partice.util.thread;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ThreadPoolUtil {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    public static void execute(Runnable task) throws InterruptedException {

        ConcurrentHashMap<String,String > m = new ConcurrentHashMap<String,String>();

        m.put("a","a");
        int a = 2;
        int b = a >>> 2;
        fixedThreadPool.submit(task);
    }


}
