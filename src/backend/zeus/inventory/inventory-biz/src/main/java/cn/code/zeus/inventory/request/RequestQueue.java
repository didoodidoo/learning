package cn.code.zeus.inventory.request;

import cn.code.zeus.inventory.thread.RequestProcessorThread;
import cn.code.zeus.inventory.thread.RequestProcessorThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@Component
public class RequestQueue {


    private List<ArrayBlockingQueue<IRequest>> queueList = new ArrayList<>();

    private RequestProcessorThreadPool requestProcessorPool;

    @Value("${queue.size}")
    int queueSize = 0;

    private static final int DEFAULT_QUEUE_SIZE = 16;

    public RequestQueue(@Autowired RequestProcessorThreadPool requestProcessorPool) {
        this.requestProcessorPool = requestProcessorPool;
        init();
    }

    public void init() {
        int size = queueSize == 0 ? DEFAULT_QUEUE_SIZE : queueSize;
        for (int i = 0; i < size; i++) {
            ArrayBlockingQueue<IRequest> workQueue = new ArrayBlockingQueue<>(1000);
            requestProcessorPool.execute(new RequestProcessorThread(workQueue));
            queueList.add(workQueue);
        }
    }

    public boolean put(IRequest request) throws InterruptedException {
        //根据请求路由 就不写了
        queueList.get(0).put(request);
        return true;
    }

}
