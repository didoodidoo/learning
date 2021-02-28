package cn.code.zeus.practice.service

import cn.code.zeus.user.pojo.UserInfo
import org.slf4j.LoggerFactory
//import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageService {


//    @KafkaListener(topics = ["topic.devops.test"], groupId = "devops")
//    fun listenMessage(message: UserInfo) {
//        logger.info("消费者消费到消息 $message")
//    }

    companion object{
        private val logger = LoggerFactory.getLogger(MessageService::class.java)
    }
}