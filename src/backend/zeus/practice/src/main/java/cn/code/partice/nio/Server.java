package cn.code.partice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;


public class Server implements AutoCloseable {


    private static final int PORT = 21940;
    private static final int BUFFER_SIZE = 1024;

    private Selector selector;
    private ServerSocketChannel channel;
    private boolean activated = false;

    public Server() throws IOException {
        init();
    }

    private void init() throws IOException {
        //创建并打开选择器
        this.selector = Selector.open();
        //创建并打开监听通道
        this.channel = ServerSocketChannel.open();
        //设置通道通讯模式为非阻塞(NIO默认为阻塞)
        this.channel.configureBlocking(false);
        //绑定监听的服务端口
        this.channel.socket().bind(new InetSocketAddress(PORT));
        //注册在服务端通道上，首先关注的事件
        this.channel.register(this.selector, SelectionKey.OP_ACCEPT);
        //设置运行状态激活
        this.activated = true;
    }


    /**
     * 服务端监听运行核心业务实现
     */
    public void run() {
        while (this.activated) {
            try {
                //运行到此方法阻塞，直到有事件发生再返回
                this.selector.select();
                //获取被监听的事件
                Set<SelectionKey> keys = this.selector.selectedKeys();
                //在迭代器中，处理不同的事件
                this.eventIterator(keys);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在迭代处理发生的事件
     *
     * @param keys 发生的事件类型
     */
    private void eventIterator(Set<SelectionKey> keys) {
        Iterator<SelectionKey> it = keys.iterator();
        while (it.hasNext()) {
            SelectionKey key = it.next();
            //这里先从迭代器移除，避免后面重复执行
            it.remove();
            //处理事件
            this.processEvent(key);
        }
    }

    private void processEvent(SelectionKey key) {
        if (key.isValid()) {
            try {
                if (key.isAcceptable())
                    processAcceptable(key);
                if (key.isReadable())
                    processReadable(key);
                if (key.isWritable())
                    processWriteable(key);
            } catch (IOException e) {
                key.cancel();
                e.printStackTrace();
            }
        }
    }

    private void processAcceptable(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        //接收客户端Socket
        SocketChannel channel = serverChannel.accept();
        //设置其为非阻塞
        channel.configureBlocking(false);
        //然后注册此通道的读事件
        channel.register(this.selector, SelectionKey.OP_READ);
        System.out.println("Build connection with client..");
    }

    private void processReadable(SelectionKey key) throws IOException {
        System.out.println("Reading client data...");
        SocketChannel channel = (SocketChannel) key.channel();
        //开辟内存空间，接收数据
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        //将数据读入缓冲区
        int length = channel.read(buffer);
        if (0 < length) {
            //读写切换
            buffer.flip();
            //更具缓冲区数据建立转换的字节数组
            byte[] bytes = new byte[buffer.remaining()];
            //从缓冲区读取字节数据
            buffer.get(bytes);
            //解码数据
            String data = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("Received data: " + data);
            //向对端发送接收应答
            String answer = "Server has received data:" + data;
            this.reply(channel, answer);
        } else if (0 > length) {
            //取消处理的事件
            key.cancel();
            channel.close();
        }
    }

    private void reply(SocketChannel channel, String msg) {
        //消息编码
        byte[] bytes = msg.getBytes();
        //开启写缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        //将数据写入缓冲区
        buffer.put(bytes);
        //切换到读事件
        buffer.flip();
        /*
         * 这里为了不出现写空或写溢出缓冲区情况，建立写事件监听同时保留之前的读监听
         * 作为监听的附件传入写操作的buffer
         */
        try {
            channel.register(this.selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, buffer);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    private void processWriteable(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        //拿到写事件的buffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        //若buffer中有数据，则刷到对端
        if (buffer.hasRemaining()) {
            int length = channel.write(buffer);
            System.out.println("Write data " + length + " byte to client.");
        } else {
            //若没有数据，则继续监听读事件
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    @Override
    public void close() {
        this.activated = false;
        try {
            //关闭选择器
            if (null != this.selector) {
                if (this.selector.isOpen()) {
                    this.selector.close();
                }
                this.selector = null;
            }
            //关闭通道
            if (null != this.channel) {
                if (this.channel.isOpen()) {
                    this.channel.close();
                }
                this.channel = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
