package cn.code.zeus.practice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class PracticeApplication

fun main(args: Array<String>) {
    SpringApplication.run(PracticeApplication::class.java,*args)
}