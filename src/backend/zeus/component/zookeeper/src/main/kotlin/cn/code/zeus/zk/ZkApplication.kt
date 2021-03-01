package cn.code.zeus.zk


import cn.code.zeus.common.web.anno.MicroService
import org.springframework.boot.SpringApplication

@MicroService
class ZkApplication

fun main(args: Array<String>) {
    SpringApplication.run(ZkApplication::class.java, *args)
}