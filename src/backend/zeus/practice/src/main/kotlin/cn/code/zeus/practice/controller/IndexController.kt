package cn.code.zeus.practice.controller

import cn.code.zeus.common.api.exception.RequestException
import cn.code.zeus.user.api.service.ServiceUserApi
import cn.code.zeus.user.pojo.UserInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
@RequestMapping("/index")
class IndexController {

    @Resource
    lateinit var serviceUserApi: ServiceUserApi

    @Value("\${app.conf}")
    lateinit var conf: String

    @ResponseBody
    @RequestMapping("/user/default")
    fun getDefaultUserInfo(): UserInfo {
        return serviceUserApi.getDefaultUserInfo()
    }

    @RequestMapping("/conf")
    fun conf(): String {
        return conf
    }


//    @RequestMapping("/{type}")
//    fun index(
//        @PathVariable("type")
//        type: String
//    ): String {
//        logger.info(type)
//        if (type == "400")
//            throw RequestException("参数不匹配")
//        return "hello"
//    }


    companion object {
        private val logger = LoggerFactory.getLogger(IndexController::class.java)
    }
}