package cn.code.odin.common.api.anno;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = "cn.code.odin")
@EnableDiscoveryClient
@EnableCircuitBreaker
public @interface MicroService {
}
