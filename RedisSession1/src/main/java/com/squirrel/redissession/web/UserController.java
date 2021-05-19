package com.squirrel.redissession.web;

import com.squirrel.redissession.model.User;
import com.squirrel.redissession.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/setSession")
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("message", request.getRequestURI());
        map.put("request url", request.getRequestURI());
        return map;
    }

    @RequestMapping("/getSession")
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return map;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String userName, String passWord) {
        String msg = "Login failure";
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassWord().equals(passWord)) {
            request.getSession().setAttribute("user", user);
            msg = "Login successful";
        }
        return msg;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "logout successful";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String msg = "index";
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            msg = "please login";
        }
        return msg;
    }
}
