package com.squirrel.jspinspringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai")));
        model.put("message", "hello world");
        return "welcome";
    }

    @GetMapping("/user")
    public String user(Map<String, Object> model, HttpServletRequest request) {
        model.put("username", "squirrel");
        model.put("salary", "0");
        request.getSession().setAttribute("count", 6);
        return "user";
    }
}
