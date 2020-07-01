package cn.code.zeus.practice

import cn.code.zeus.common.web.anno.MicroService
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig
import org.springframework.boot.SpringApplication

@MicroService
@EnableApolloConfig
class PracticeApplication

fun main(args: Array<String>) {
    SpringApplication.run(PracticeApplication::class.java,*args)
}