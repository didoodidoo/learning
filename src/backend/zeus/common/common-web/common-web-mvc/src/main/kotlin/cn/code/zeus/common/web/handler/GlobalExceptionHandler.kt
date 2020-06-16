package cn.code.zeus.common.web.handler

import cn.code.zeus.common.api.exception.RequestException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * SpringMvc的全局异常捕获
 */
// @ControllerAdvice 这个注解
@ControllerAdvice
class GlobalExceptionHandler {

    //    用于捕获异常  状态码设为400
    @ExceptionHandler(RequestException::class)
    @ResponseBody
    fun handleRequestException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        e: RequestException
    ): String {
        response.status = 400
        return e.message ?: "异常操作"
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseBody
    fun handleRuntimeException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        e: RequestException
    ): String {
        response.status = 500
        return "系统内部发生错误,请联系管理员解决"
    }

}