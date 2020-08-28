package cn.code.partice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Client {

    //服务端主机
    private final String HOST = "127.0.0.1";
    //服务端口
    private final int PORT = 21941;

    private final int BUFFER_SIZE = 1024;
    /**
     * 定义NIO选择器:用于注册和监听事件
     * 选择监听的事件类型: OP_READ 读事件 / OP_WRITE 写事件
     * OP_CONNECT 客户端连接事件 / OP_ACCEPT 服务端接收通道连接事件
     */
    private Selector selector = null;
    //定义客户端连接通道
    private SocketChannel channel = null;
    //运行状态是否被激活
    private volatile boolean activated = false;

    public Client(String host, int port) {
        this.init();
    }

    /**
     * 处理器初始化
     * 负责建立连接准备工作
     */
    private void init() {
        try {
            //创建并打开选择器
            this.selector = Selector.open();
            //建立并打开监听通道
            this.channel = SocketChannel.open();
            //设置通道通讯模式为非阻塞，NIO默认为阻塞式的
            this.channel.configureBlocking(false);
            //激活运行状态
            this.activated = true;
        } catch (IOException e) {
            e.printStackTrace();
            this.stop();
        }
    }

    /**
     * 连接服务器
     */
    private void connect() {
        try {
            //连接服务端：因为之前设置了通讯模式为非阻塞
            // 这里会立即返回TCP握手是否已建立
            if (this.channel.connect(new InetSocketAddress(this.HOST, this.PORT))) {
                //连接建立后，在通道上注册读事件关注，客户端一接收到数据立即触发处理
                this.channel.register(this.selector, SelectionKey.OP_READ);
            } else {
                //若连接握手未建立，则在通道上继续关注连接事件，一旦连接建立继续进行后续的处理逻辑
                this.channel.register(this.selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.stop();
        }
    }

    /**
     * 选择器事件迭代处理
     *
     * @param keys 选择器事件KEY
     */
    private void eventIterator(Set<SelectionKey> keys) {
        //这里采用迭代器，因为需要迭代时对key进行移除操作
        Iterator<SelectionKey> it = keys.iterator();
        while (it.hasNext()) {
            SelectionKey key = it.next();
            //这里先移除事件key,避免多次处理
            it.remove();
            //处理迭代事件
            this.processEvent(key);
        }
    }

    /**
     * 处理发生的事件
     *
     * @param key 选择器事件KEY
     */
    private void processEvent(SelectionKey key) {
        //只对有效的事件类型进行处理
        if (key.isValid()) {
            try {
                //在事件通道上处理
                SocketChannel socketChannel = (SocketChannel) key.channel();
                /*处理连接就绪事件
                 */
                if (key.isConnectable()) {
                    //检测连接是否完成，避免发生导致NotYetConnectedException异常
                    if (socketChannel.finishConnect()) {
                        System.out.println("Has completed connection with server..");
                        /*
                         * 在通道上关注读事件，NO的写事件一般不特别关注，
                         * 原因：写缓冲区大部分时间被认为是空闲的，会频繁被选择器选择(会浪费CPU资源)，
                         *       所以不应该频繁被注册；
                         * 只有在写的数据超过写缓冲区可用空间时，把一部分数据刷出缓冲区后，
                         * 有空间时再通知应用程序进行写；
                         * 且应用程序写完后，应立即关闭写事件
                         */
                        socketChannel.register(this.selector, SelectionKey.OP_READ);
                    } else {//这里若连接仍未建立一般视为网络或其他原因，暂时退出
                        this.stop();
                    }
                }
                /*
                 * 处理读事件
                 */
                if (key.isReadable()) {
                    //开辟内存缓冲区，这里用JVM堆内存
                    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    //将通道中的数据读到缓冲区
                    int length = socketChannel.read(buffer);
                    if (0 < length) {
                        /*
                         * 进行读写转换，NIO固定范式
                         */
                        buffer.flip();
                        //获取buffer可用空间
                        int size = buffer.remaining();
                        byte[] bytes = new byte[size];
                        //读Buffer
                        buffer.get(bytes);
                        //获取缓冲区数据
                        String result = new String(bytes, StandardCharsets.UTF_8);
                        System.out.println("Received server message: " + result);
                    } else if (0 > length) {
                        //取消关注当前事件，关闭通道
                        key.cancel();
                        socketChannel.close();
                    }
                }
            } catch (Exception e) {
                key.cancel();
                if (null != key.channel()) {
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                e.printStackTrace();
            }
        }
    }

    public void write(String data) {
        try {
            byte[] bytes = data.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            //将数据放入写缓冲区
            buffer.put(bytes);
            buffer.flip();
            this.channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止运行
     */
    public void stop() {
        this.activated = false;
        System.exit(-1);
    }

    /**
     * 客户端通讯业务核实现
     */
    public void run() {
        //建立服务器连接
        this.connect();
        //持续监听各种事件的发生
        while (this.activated) {
            try {
                //监听事件是否发生，若发生直接返回；反之阻塞至事件发生
                this.selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                this.stop();
            }
            //获取发生事件的类型
            Set<SelectionKey> keys = this.selector.selectedKeys();
            //迭代处理事件
            this.eventIterator(keys);
        }
        //关闭选择器
        if (null != this.selector) {
            try {
                this.selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.stop();
    }
}