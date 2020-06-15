package cn.code.practice.controller;


import cn.code.zeus.common.api.exception.RequestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;


@Controller
public class IndexController {

    @RequestMapping("/index/type")
    public String index( ){

        String type = "4";
        if(type.equals("400"))
            throw new RequestException("400");

        if(type.equals("500"))
            throw new RuntimeException("500");

        return "what fuck";
    }
}
