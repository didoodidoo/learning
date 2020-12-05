package cn.code.partice.nettydemo.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;

public class BufferDemo {

    @Test
    public void bufferTest() {

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(8, 64);
        print(buffer);
        buffer.writeBytes("hello".getBytes());
        print(buffer);

    }

    public static void print(ByteBuf buf) {
        System.out.println("是否可写：" + buf.isReadable());
        System.out.println(" :" + buf.readerIndex());
        System.out.println(" :" + buf.readableBytes());
        System.out.println(" :" + buf.writerIndex());
    }
}
