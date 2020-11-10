package cn.code.partice.nio.b;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Server {

    private ServerSocketChannel server = null;
    //多路复用器
    private Selector selector = null;
    int port = 9090;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                //去内核里取出有效的连接事件 这里会不会阻塞？
                while (selector.select(0) > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    //处理各个客户端的请求
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        handle(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            acceptHandle(key);
        } else {
            readHandle(key);
        }
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
                System.out.println("client info .."+ new String(buffer.array()));
                buffer.clear();
            } else if (read == 0) {
                break;
            } else {//-1 情况会空转
                System.out.println("connection close .."+client.getRemoteAddress());
                client.close();
                break;
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

    public static void main(String[] args) {
        Server s = new Server();
        s.initServer();
        System.out.println("server start at " + new Date());
        s.run();

    }

}
