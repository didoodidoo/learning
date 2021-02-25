package cn.code.partice.singleton;

public class Singleton2 {
    private Singleton2() {
    }

    private static class InstanceHolder {
        final static Singleton2 instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return InstanceHolder.instance;
    }

}
