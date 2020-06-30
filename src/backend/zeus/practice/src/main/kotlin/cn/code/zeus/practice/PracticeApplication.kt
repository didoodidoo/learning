package cn.code.zeus.practice

import cn.code.zeus.common.web.anno.MicroService
import org.springframework.boot.SpringApplication

@MicroService
class PracticeApplication

fun main(args: Array<String>) {
    SpringApplication.run(PracticeApplication::class.java,*args)
}