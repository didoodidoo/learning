package cn.code.odin.user;

import cn.code.odin.common.api.anno.MicroService;
import org.springframework.boot.SpringApplication;


@MicroService
public class OdinUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdinUserApplication.class, args);
    }

}
