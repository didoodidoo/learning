package cn.code.partice.netty.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//处理key
public class WorkThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(WorkThread.class);

    SelectionKey key;

    public WorkThread(SelectionKey key) {
        this.key = key;
    }

    @Override
    public void run() {
        try {
            logger.info("work thread handle key...");
            if (key.isAcceptable()) {
                acceptHandler(key);
            } else if (key.isReadable()) {
                readHandle(key);
            } else if (key.isWritable()) {
                writeHandler(key);
            }
        } catch (IOException e) {
            logger.error("处理请求出错", e);
        }
    }

    private void writeHandler(SelectionKey key) {

    }

    private void readHandle(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        while (true) {
            read = client.read(buffer);
            if (read > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    client.write(buffer);
                }
                System.out.println("client info .." + new String(buffer.array()));
                buffer.clear();
            } else if (read == 0) {
                break;
            } else {//-1 情况会空转
                System.out.println("connection close .." + client.getRemoteAddress());
                client.close();
                break;
            }
        }

    }

    private void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel client = server.accept();
        client.configureBlocking(false);

    }
}
