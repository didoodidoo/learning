package cn.code.zeus.netty.handler

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.*
import io.netty.util.CharsetUtil
import org.slf4j.LoggerFactory

class MyHttpHandler :
    SimpleChannelInboundHandler<FullHttpRequest>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: FullHttpRequest?) {
        val content = "Receive http request, uri: ${msg?.uri()}, method: ${msg?.method()}, content: ${
            msg?.content()?.toString(CharsetUtil.UTF_8)
        }"
        logger.info(content)
        val byteArray = ByteArray(msg?.content()?.readableBytes()!!)
        msg.content()?.readBytes(byteArray)
        val str = String(byteArray)
        logger.info(str)
        val response = DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1,
            HttpResponseStatus.OK,
            Unpooled.wrappedBuffer(content.toByteArray())
        )
        ctx?.writeAndFlush(response)?.addListener(ChannelFutureListener.CLOSE)

    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyHttpHandler::class.java)
    }
}

