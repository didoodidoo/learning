package cn.code.partice.nio.c;


import java.nio.ByteBuffer;

public class Boss {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        System.out.println("buffer：初始值"+buffer);
        buffer.put("dell".getBytes());
        System.out.println("buffer：写了数据"+buffer);
        buffer.flip();
        System.out.println("buffer：flip "+buffer);
        buffer.compact();
        System.out.println("buffer compact"+buffer);
    }

}
