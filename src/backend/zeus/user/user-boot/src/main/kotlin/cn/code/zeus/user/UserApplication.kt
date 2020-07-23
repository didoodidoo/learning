package cn.code.zeus.user

import cn.code.zeus.common.web.anno.MicroService
import org.springframework.boot.SpringApplication

@MicroService
class UserApplication

fun main(args: Array<String>) {
    SpringApplication.run(UserApplication::class.java, *args)
}