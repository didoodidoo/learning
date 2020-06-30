package cn.code.zeus.common.web

import feign.Feign
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.annotation.AnnotatedElementUtils
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


/**
 * 排除RequestMapping被FeignClient和SpringMVC重复扫描
 */
@Configuration
@ConditionalOnClass(Feign::class)
@PropertySource("classpath:/common-web.properties")
class FeignConfiguration {

    private class FeignRequestMappingHandlerMapping : RequestMappingHandlerMapping() {
        override fun isHandler(beanType: Class<*>?): Boolean {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(
                        beanType,
                        FeignClient::class.java
                    )
        }
    }

    @Bean
    fun feignWebRegistrations(): WebMvcRegistrations {
        return object : WebMvcRegistrations{
            override fun getRequestMappingHandlerMapping(): RequestMappingHandlerMapping {
                return FeignRequestMappingHandlerMapping()
            }
        }
    }
}