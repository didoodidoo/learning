package cn.code.zeus.netty.server

import cn.code.zeus.netty.handler.MyHttpHandler
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.*
import org.slf4j.LoggerFactory


class NettyServer {
//    单例

    private val port:Int = 9090


    fun start() {
        val b = ServerBootstrap()
        val group = NioEventLoopGroup()
        b.group(group)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(
                object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.pipeline()
                            ?.addLast("codec", HttpServerCodec())
                            ?.addLast("compressor", HttpContentCompressor())
                            ?.addLast("aggregator", HttpObjectAggregator(512 * 1024))
                            ?.addLast("handler", MyHttpHandler())
                    }
                }
            ).option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
       val f =  b.bind(port).sync()
        logger.info("server start: $port")
        f.channel().closeFuture().sync()
    }


    companion object {
        private val logger = LoggerFactory.getLogger(NettyServer::class.java)
    }
}

