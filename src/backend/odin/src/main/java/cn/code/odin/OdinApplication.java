package cn.code.odin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class OdinApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdinApplication.class, args);
    }

}
