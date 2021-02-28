package cn.code.partice.redisson.conf;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissionConfiguration {

//    @Bean
//    public RedissonClient getRedisson() {
//        Config config = new Config();
//        //单机模式  依次设置redis地址和密码
//        config.useSingleServer().
//                setAddress("redis://120.78.189.131:6379").
//                setPassword("1qaz@WSX");
//        return Redisson.create(config);
//    }

}
