package cn.code.partice.redisson;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonExample {

    public static RedissonClient getRedisson() {
        Config config = new Config();
        //单机模式  依次设置redis地址和密码
        config.useSingleServer().
                setAddress("redis://120.78.189.131:6379").
                setPassword("1qaz@WSX");
        return Redisson.create(config);
    }

    @Test
    public void test() throws InterruptedException {
        RedissonClient client = getRedisson();
        RLock lock = client.getLock("devops-ci-redisson-lock");
//        一个10s后自动释放的锁
        System.out.println("lock for:devops-ci-redisson-lock");
        lock.lock(10, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("释放锁");
        lock.unlock();
    }

}
