package cn.code.zeus.bytcc

import cn.code.zeus.common.web.anno.MicroService
import org.springframework.boot.SpringApplication

@MicroService
class TccService3Application

fun main(args: Array<String>) {
    SpringApplication.run(TccService3Application::class.java, *args)
}