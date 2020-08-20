package cn.code.zeus.user.api.fail

import cn.code.zeus.user.api.service.ServiceUserApi
import cn.code.zeus.user.pojo.UserInfo
import org.springframework.stereotype.Component

@Component
class ServiceUserApiFailImpl :ServiceUserApi {

    override fun getDefaultUserInfo(): UserInfo{
        return UserInfo("fail","fail","404","404")
    }

}