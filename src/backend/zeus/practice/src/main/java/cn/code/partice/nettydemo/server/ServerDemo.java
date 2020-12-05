package cn.code.partice.nettydemo.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;

public class ServerDemo {

    @Test
    public void serverTest() {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();

        eventExecutors.register(server);
        ChannelPipeline pipeline = server.pipeline();
        pipeline.addLast(new MyAcceptHandler());
        server.bind(new InetSocketAddress(8081));


    }
}

class MyAcceptHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        System.out.println("server registered");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }


}