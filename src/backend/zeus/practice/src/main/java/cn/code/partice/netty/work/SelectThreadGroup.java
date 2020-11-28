package cn.code.partice.netty.work;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectThreadGroup {

    SelectorThread[] selectorThreads;

    ExecutorService pool;

    ServerSocketChannel server;

    AtomicInteger xid = new AtomicInteger(0);

    LinkedBlockingQueue<SelectionKey> queue = new LinkedBlockingQueue<>();

    public SelectThreadGroup(int threadCount) {
//        threadCount 线程数
        pool = Executors.newFixedThreadPool(threadCount);
        selectorThreads = new SelectorThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            selectorThreads[i] = new SelectorThread(queue);
        }
//      工作线程启动
        new Thread(new WorkGroupThread(5, queue)).start();
    }

    public void bind(int port) throws IOException {
        server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(port));
//      这个server需要注册到selector上去
        nextSelector(server);
//      我不能在这里再启动线程吗？ 就不需要那些东西了。。。
    }

    private void nextSelector(ServerSocketChannel channel) throws ClosedChannelException {
//    server 和 client 选线程进行传递 处理
        SelectorThread st = next();
        channel.register(st.selector, SelectionKey.OP_ACCEPT);
//      启动线程
        pool.submit(st);
    }

    private SelectorThread next() {
        int index = xid.incrementAndGet() % selectorThreads.length;
        return selectorThreads[index];
    }

}
