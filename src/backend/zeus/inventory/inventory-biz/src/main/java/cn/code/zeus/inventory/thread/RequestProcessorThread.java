package cn.code.zeus.inventory.thread;

import cn.code.zeus.inventory.request.IRequest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class RequestProcessorThread implements Callable<Boolean> {

    private ArrayBlockingQueue<IRequest> workQueue;

    public RequestProcessorThread(ArrayBlockingQueue<IRequest> workQueue){
        this.workQueue = workQueue;
    }

    @Override
    public Boolean call() throws InterruptedException {
        workQueue.take().process();
        return true;
    }
}
