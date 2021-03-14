package cn.code.zeus.bytetcc

import cn.code.zeus.common.api.pojo.Result
import org.bytesoft.compensable.Compensable
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
@Compensable(interfaceClass = Service1Api::class, confirmableKey = "serviceApiConfirm", cancellableKey = "serviceApiCancel")
class ServiceApiImpl : Service1Api {

    override fun doTx(): Result<String> {
        logger.info("bytetcc:服务1进行服务")
        return Result("success")
    }

    companion object {
        val logger = LoggerFactory.getLogger(ServiceApiImpl::class.java)
    }
}