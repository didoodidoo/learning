package cn.code.zeus.zk.controller

import org.apache.curator.framework.CuratorFramework
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/index")
class IndexController {


    @Autowired
    lateinit var curatorFramework: CuratorFramework

    @GetMapping("/val/{val}")
    fun setValue(
            @PathVariable("val")
            value: String
    ): String? {
        logger.info("try create node")
        curatorFramework
                .create()
                .creatingParentsIfNeeded()
                .forPath("/usr/default", value.toByteArray())
        val v = curatorFramework.data.forPath("/usr/default")
        return String(v)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(IndexController::class.java)
    }
}