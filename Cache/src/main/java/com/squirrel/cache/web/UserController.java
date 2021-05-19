package com.squirrel.cache.web;

import com.squirrel.cache.model.User;
import com.squirrel.cache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/hello")
    @Cacheable(value = "helloCache")
    public String hello(String name) {
        System.out.println("not using cache");
        return "hello " + name;
    }

    @RequestMapping("/condition")
    @Cacheable(value = "condition", condition = "#name.length() <=4")
    public String condition(String name) {
        System.out.println("not using cache");
        return "hello " + name;
    }

    @RequestMapping("/getUsers")
    @Cacheable(value = "usersCache", key = "#nickName", condition = "#nickName.length() >= 6")
    public List<User> getUsers(String nickName) {
        List<User> users = userRepository.findByNickName(nickName);
        System.out.println("searched in database 1");
        return users;
    }

    @RequestMapping("/getPutUsers")
    @CachePut(value = "usersCache", key = "#nickName")
    public List<User> getPutUsers(String nickName) {
        List<User> users = userRepository.findByNickName(nickName);
        System.out.println("searched in database 2");
        return users;
    }

    @RequestMapping("/allEntries")
    @CacheEvict(value = "usersCache", allEntries = true)
    public String allEntries(String nickName) {
        String msg = "cleared allEntries";
        System.out.println(msg);
        return msg;
    }

    @RequestMapping("/beforeInvocation")
    @CacheEvict(value = "usersCache", allEntries = true, beforeInvocation = true)
    public void beforeInvocation() {
        throw new RuntimeException("test beforeInvocation");
    }
}
