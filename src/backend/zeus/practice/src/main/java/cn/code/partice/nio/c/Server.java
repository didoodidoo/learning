package cn.code.partice.nio.c;

import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Server {

    private ArrayBlockingQueue<SocketChannel> queue;
//  boss 负责accept，accpet之后把请求放到queue里面去
//  worker负责处理queue里面的请求
    private Boss boss;

    private List<Worker> workList;
}
