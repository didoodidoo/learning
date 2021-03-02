package cn.code.zeus.zk.controller

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.locks.InterProcessMutex
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/index")
class IndexController {


    @Autowired
    lateinit var client: CuratorFramework

    @GetMapping("/val/{val}")
    fun setValue(
            @PathVariable("val")
            value: String
    ): String? {
        logger.info("try create node")
        client
                .create()
                .creatingParentsIfNeeded()
                .forPath("/usr/default", value.toByteArray())
        val v = client.data.forPath("/usr/default")
        return String(v)
    }

    @GetMapping("/lock/{val}")
    fun lock(
        @PathVariable("val")
        value: String
    ): String? {
        logger.info("try lock for $value")
        val lockPath = "/lock-default"
        val lock = InterProcessMutex(client,lockPath)
        lock.acquire()
        lock.release()
        val v = client.data.forPath("/usr/default")
        return String(v)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(IndexController::class.java)
    }
}