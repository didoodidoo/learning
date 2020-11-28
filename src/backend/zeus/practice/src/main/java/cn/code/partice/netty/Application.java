package cn.code.partice.netty;

import cn.code.partice.netty.work.SelectThreadGroup;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
//        在这启动
        SelectThreadGroup selectThreadGroup = new SelectThreadGroup(3);
        try {
            selectThreadGroup.bind(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
