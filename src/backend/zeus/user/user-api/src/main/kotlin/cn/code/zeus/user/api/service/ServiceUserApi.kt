package cn.code.zeus.user.api.service

import cn.code.zeus.user.pojo.UserInfo
import cn.code.zeus.user.util.SERVICE_NAME
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient(SERVICE_NAME)
@RequestMapping("/service/userInfo")
interface ServiceUserApi {

    @Headers("Content-Type: application/json","Accept: application/json")
    @GetMapping("/default")
    fun getDefaultUserInfo(): UserInfo

}