package cn.code.partice.netty.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class SelectorThread implements Runnable {

    //    每个线程对应一个selector
    Selector selector;
    private static final Logger logger = LoggerFactory.getLogger(SelectorThread.class);

    LinkedBlockingQueue<SelectionKey> queue;

    public SelectorThread(LinkedBlockingQueue<SelectionKey> queue) {
        try {
            selector = Selector.open();
            this.queue = queue;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                handleSelector();
            } catch (IOException e) {
                logger.error("出错", e);
            } catch (InterruptedException e2) {
                logger.error("中断异常", e2);
            }
        }
    }

    private void handleSelector() throws IOException, InterruptedException {
//        logger.info("selector select blocking... {}", selector.keys().size());
        int nums = selector.select();
//        logger.info("selector after select  {}", selector.keys().size());
        //处理select keys
        if (nums > 0) {
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
//                logger.info("selector handle  key");
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable())
                    acceptHandle(key);
                else if (key.isReadable())
                    queue.put(key);
            }
        }
    }

    private void acceptHandle(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        //接收客户端Socket
        SocketChannel clientChannel = serverChannel.accept();
        //设置其为非阻塞
        clientChannel.configureBlocking(false);
        //然后注册此通道的读事件
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        clientChannel.register(this.selector, SelectionKey.OP_READ, buffer);
        System.out.println("Build connection with client.." + clientChannel.getRemoteAddress());
    }
}
