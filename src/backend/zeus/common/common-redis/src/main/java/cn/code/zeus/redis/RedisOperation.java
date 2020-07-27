package cn.code.zeus.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisOperation {
    private RedisTemplate<String,String> redisTemplate;

    public RedisOperation(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public String getAndSet(String key,String defaultValue,long expireInSecond){
        String value = redisTemplate.opsForValue().getAndSet(key, defaultValue);
        if (value == null) {
            redisTemplate.expire(key, expireInSecond, TimeUnit.SECONDS);
        }
        return value;
    }

}
