package cn.code.partice.nettydemo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.net.InetSocketAddress;

public class ClientDemo {


    @Test
    public void clientTest() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        NioSocketChannel client = new NioSocketChannel();
        group.register(client);

        client.pipeline().addLast(new MyHandler());


        ChannelFuture connect = client.connect(new InetSocketAddress("127.0.0.1", 8080));
        ChannelFuture sync = connect.sync();

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes());
        ChannelFuture send = client.writeAndFlush(byteBuf);
        send.sync();

        sync.channel().closeFuture().sync();
        System.out.println("finish...");

    }

}



class MyHandler  extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("registered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("actived");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        CharSequence charSequence = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(charSequence);
        ctx.writeAndFlush("response".getBytes());
    }
}