package cn.code.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@Controller
public class indexController {


    @RequestMapping("/index/{type}")
    public String index(
            @PathParam("type")
            String type
    ){
        if(type=="400")
        return "index";
    }
}
