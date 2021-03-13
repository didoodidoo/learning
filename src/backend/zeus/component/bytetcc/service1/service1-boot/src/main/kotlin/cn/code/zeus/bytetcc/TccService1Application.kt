package cn.code.zeus.bytetcc

import cn.code.zeus.common.web.anno.MicroService
import org.springframework.boot.SpringApplication

@MicroService
class TccService1Application

fun main(args: Array<String>) {
    SpringApplication.run(TccService1Application::class.java, *args)
}