package cn.code.zeus.bytetcc

import cn.code.zeus.common.api.pojo.Result
import feign.Headers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

//@FeignClient(SERVICE_NAME,fallback = ServiceUserApiFailImpl::class)
@Headers("Content-Type: application/json", "Accept: application/json")
@RequestMapping("/service/index")
interface Service1Api {

    @GetMapping("/default")
    fun getDefaultUserInfo(): Result<String>

}