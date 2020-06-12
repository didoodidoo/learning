package cn.code.test.common;

/**
 * 雪花算法ID 生成器
 */
public class SnowFlakeGenerator {

    //    工作id和数据id合起来是机器id
    //5位的工作ID
    private long workId;
    //5位的数据id
    private long dataId;
    //    12位的序列号
    private long sequence;

    //    这个算最大值的操作  -1的补码表示是各位取反末位加一 即 1111111111111111
//    把它左移五位1111111111100000在和-1进行异或操作得到的一个正的11111 就是五位的最大数
//    原来是 -1L ^ (-1L << workIdBit) 这样的  IDEA给优化成了下面的 ~(-1L << workIdBits)
    private final long workIdBits = 5L;
    private final long dataIdBits = 5L;

    //序列号id长度
    private long sequenceBits = 12L;
    //序列号最大值
    private long sequenceMask = ~(-1L << sequenceBits);

    // 初始时间戳
    private final long twepoch = 1591689525000L;
    //上次时间戳，初始值为负数
    private long lastTimestamp = -1L;

    //工作id需要左移的位数，12位
    private long workIdShift = sequenceBits;
    //数据id需要左移位数 12+5=17位
    private long dataIdShift = sequenceBits + workIdBits;
    //时间戳需要左移位数 12+5+5=22位
    private long timestampLeftShift = sequenceBits + workIdBits + dataIdBits;

    public SnowFlakeGenerator(long workId, long dataId, long sequence) {
        long maxWorkId = ~(-1L << workIdBits);
        if (workId > maxWorkId || workId < 0)
            throw new IllegalArgumentException(String.format("work Id can't be greater than %d or less than 0", maxWorkId));
        long maxDataId = ~(-1L << dataIdBits);
        if (dataId > maxDataId || dataId < 0)
            throw new IllegalArgumentException(String.format("data Id can't be greater than %d or less than 0", maxDataId));
        this.workId = workId;
        this.dataId = dataId;
        this.sequence = sequence;
    }


    public synchronized long generate() {
        long timestamp = System.currentTimeMillis();
        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常 出现闰秒的时候系统会回拨 建议加入重试
        if (timestamp < lastTimestamp)
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));

        //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        //将上次时间戳值刷新
        lastTimestamp = timestamp;

        //将各位左移响应的位数在相与得到对应的id
        return ((timestamp - twepoch) << timestampLeftShift) |
                (dataId << dataIdShift) |
                (workId << workIdShift) |
                sequence;
    }

    //获取时间戳，并与上次时间戳比较
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

}
