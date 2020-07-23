package cn.code.zeus.practice

import cn.code.zeus.common.web.anno.MicroService
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@MicroService
@EnableApolloConfig
class PracticeApplication

fun main(args: Array<String>) {
    val a = "{\"principal_id\":\"userId\",\"principal_type\":\"\",\"scope_type\":\"system\",\"scope_id\":\"bk_ci\",\"resource_types_actions\":[{\"action_id\":\"view\",\"resource_type\":\"project\"}],\"resource_data_type\":\"\",\"is_exact_resource\":false}"
    AnnotationConfigApplicationContext()
    SpringApplication.run(PracticeApplication::class.java, *args)
}