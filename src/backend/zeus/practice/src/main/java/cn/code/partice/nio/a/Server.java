package cn.code.partice.nio.a;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {


    public static void main(String[] args) throws Exception {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(8080));
        ss.configureBlocking(false);
        List<SocketChannel> clients = new CopyOnWriteArrayList<>();
        read(clients);
        while (true) {
            Thread.sleep(1000);

            SocketChannel client = ss.accept();
            if (null == client) {
                System.out.println(new Date() + " client null ... ... ");
            } else {
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println(port + " connect...");
                clients.add(client);
            }
        }
    }

    private static void read(List<SocketChannel> clients) {
        new Thread(() -> {
            try {
                System.out.println("read thread start");
                while (true) {
                    Thread.sleep(1000);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    for (SocketChannel client : clients) { // 注意 -1
                        int num = client.read(buffer);
                        if (num > 0) {
                            buffer.flip();
                            byte[] info = new byte[buffer.limit()];
                            buffer.get(info);
                            System.out.println(client.socket().getPort() + ": " + new String(info));
                            buffer.clear();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
