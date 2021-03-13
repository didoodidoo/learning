package cn.code.zeus.bytetcc

import cn.code.zeus.common.web.anno.MicroService
import org.springframework.boot.SpringApplication

@MicroService
class TccService2Application

fun main(args: Array<String>) {
    SpringApplication.run(TccService2Application::class.java, *args)
}