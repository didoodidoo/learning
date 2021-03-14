package cn.code.zeus.bytetcc

import cn.code.zeus.common.api.pojo.Result
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient("service-tcc-a")
@Headers("Content-Type: application/json", "Accept: application/json")
@RequestMapping("/service/index")
interface Service1Api {

    @GetMapping("/tx")
    fun doTx(): Result<String>

}