package cn.code.zeus.common.web

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@Configuration
@PropertySource("classpath:/common-web.properties")
class MvcAutoConfiguration {
//把这个类写到  spring.factories中去在加载的时候就会自动加载这个类
//    同时 在这个类里面用工厂方法把创建bean




}