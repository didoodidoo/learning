package inventory

import cn.code.zeus.common.web.anno.MicroService
import org.springframework.boot.SpringApplication

@MicroService
class InventoryApplication

fun main(args: Array<String>) {
    SpringApplication.run(InventoryApplication::class.java, *args)
}