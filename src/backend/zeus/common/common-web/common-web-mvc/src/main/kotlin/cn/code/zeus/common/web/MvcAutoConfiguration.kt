package cn.code.zeus.common.web

import cn.code.zeus.common.web.handler.GlobalExceptionHandler
import feign.Feign
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


@Configuration
@PropertySource("classpath:/common-web.properties")
class MvcAutoConfiguration {

//    @Bean
//    fun globalExceptionHandler(): GlobalExceptionHandler {
//        return GlobalExceptionHandler()
//    }

    @Bean
    @ConditionalOnClass(Feign::class)
    fun feignWebRegistrations(): WebMvcRegistrations {
        return object : WebMvcRegistrations {
            override fun getRequestMappingHandlerMapping(): RequestMappingHandlerMapping {
                return FeignRequestMappingHandlerMapping()
            }
        }
    }

}