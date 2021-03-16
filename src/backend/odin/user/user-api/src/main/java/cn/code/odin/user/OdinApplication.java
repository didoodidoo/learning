package cn.code.odin.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = "cn.code.odin")
@EnableDiscoveryClient
public class OdinApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdinApplication.class, args);
    }

}
