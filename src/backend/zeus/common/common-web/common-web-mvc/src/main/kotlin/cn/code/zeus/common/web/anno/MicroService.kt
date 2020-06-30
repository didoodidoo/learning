package cn.code.zeus.common.web.anno

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["cn.code.zeus"])
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ["cn.code.zeus"])
annotation class MicroService