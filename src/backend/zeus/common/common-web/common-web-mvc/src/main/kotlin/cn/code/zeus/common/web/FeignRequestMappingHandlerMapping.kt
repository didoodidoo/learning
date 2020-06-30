package cn.code.zeus.common.web

import feign.Feign
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.annotation.AnnotatedElementUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


/**
 * 排除RequestMapping被FeignClient和SpringMVC重复扫描
 */
class FeignRequestMappingHandlerMapping : RequestMappingHandlerMapping() {
    override fun isHandler(beanType: Class<*>?): Boolean {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller::class.java) ||
                AnnotatedElementUtils.hasAnnotation(beanType, RestController::class.java)
    }
}
