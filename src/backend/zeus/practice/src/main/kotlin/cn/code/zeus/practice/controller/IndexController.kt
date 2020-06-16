package cn.code.zeus.practice.controller

import cn.code.zeus.common.api.exception.RequestException
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @RequestMapping("/index/{type}")
    fun index(
        @PathVariable("type")
        type: String
    ): String {
        logger.info(type)
        if (type == "400")
            throw RequestException("参数不匹配")
        return "hello"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(IndexController::class.java)
    }
}