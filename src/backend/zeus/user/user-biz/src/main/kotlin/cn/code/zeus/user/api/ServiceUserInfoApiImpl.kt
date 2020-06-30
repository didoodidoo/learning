package cn.code.zeus.user.api

import cn.code.zeus.user.api.service.ServiceUserApi
import cn.code.zeus.user.pojo.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController


@RestController
class ServiceUserInfoApiImpl: ServiceUserApi {

    override fun getDefaultUserInfo(): UserInfo {
        return UserInfo("default","默认用户","18812345678","zeus@123.com")
    }
}