package cn.code.zeus.zk.conf

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CuratorConfiguration {

    @Bean
    fun curatorFramework(): CuratorFramework {
        val client = CuratorFrameworkFactory.newClient("120.78.189.131:2181", ExponentialBackoffRetry(1000, 3))
        client.start();
        return client;
    }


}