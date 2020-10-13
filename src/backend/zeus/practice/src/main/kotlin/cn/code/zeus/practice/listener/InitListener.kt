package cn.code.zeus.practice.listener

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

@Component
class InitListener :ServletContextListener {

    companion object{
        private val logger = LoggerFactory.getLogger(InitListener::class.java)
    }
    override fun contextInitialized(sce: ServletContextEvent?) {
        logger.info("web系统启动")
    }


    override fun contextDestroyed(sce: ServletContextEvent?) {

    }
}