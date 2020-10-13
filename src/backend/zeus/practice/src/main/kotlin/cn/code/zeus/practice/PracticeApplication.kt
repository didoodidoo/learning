package cn.code.zeus.practice

import cn.code.zeus.common.web.anno.MicroService
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@MicroService
class PracticeApplication

fun main(args: Array<String>) {
    SpringApplication.run(PracticeApplication::class.java, *args)
}