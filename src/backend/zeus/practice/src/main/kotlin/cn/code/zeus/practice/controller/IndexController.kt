package cn.code.zeus.practice.controller

import cn.code.zeus.common.api.exception.RequestException
import org.apache.kafka.clients.admin.AdminClient
import org.springframework.kafka.core.KafkaTemplate
import cn.code.zeus.user.api.service.ServiceUserApi
import cn.code.zeus.user.pojo.UserInfo
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit
import javax.annotation.Resource


@RestController
@RequestMapping("/index")
class IndexController {

    @Resource
    lateinit var serviceUserApi: ServiceUserApi
//
//
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, Any>

    @Autowired
    lateinit var adminClient: AdminClient
//
//    @Value("\${app.conf}")
//    lateinit var conf: String = "conf"

    @ResponseBody
    @RequestMapping("/user/default")
    fun getDefaultUserInfo(): UserInfo {
        return serviceUserApi.getDefaultUserInfo()
    }

    @RequestMapping("/conf")
    fun conf(): String {
        return "conf"
    }

    @RequestMapping("/kafka")
    fun kafka(
        @RequestParam("message")
        message: String
    ): String {
        kafkaTemplate.send("topic.devops.test", UserInfo(message, message, "c", "d"))
        return "发送成功"
    }

    @RequestMapping("/topic")
    fun kafkaTopic(): String {
//        val topic = NewTopic("topic.devops.test", 1, 1.toShort())
//        adminClient.createTopics(listOf(topic))
        adminClient.listTopics()
        return adminClient.listTopics().names().toString()
    }


    @RequestMapping("/{type}")
    fun index(
        @PathVariable("type")
        type: String
    ): String {
        logger.info(type)
        if (type == "400")
            throw RequestException("参数不匹配")
        return "hello"
    }


    @Autowired
    lateinit var redisson: RedissonClient

    @GetMapping("/lock/{lock}")
    fun conf(
            @PathVariable("lock") lockStr: String
    ): String? {
        logger.info("lock for $lockStr")
        val lock1 = redisson.getFairLock(lockStr)
        lock1.lock()
        TimeUnit.SECONDS.sleep(15)
        lock1.unlock()
        logger.info("unlock for $lockStr")
        // 最多尝试100s 最多持有时间50s到期后自己释放
        val locked = lock1.tryLock(100, 50, TimeUnit.SECONDS)
        if (locked)
            lock1.unlock();
        return "success"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(IndexController::class.java)
    }
}