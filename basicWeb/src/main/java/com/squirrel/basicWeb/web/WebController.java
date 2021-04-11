package com.squirrel.basicWeb.web;

import com.squirrel.basicWeb.model.MyUser;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController // 类中所有的方法都会以JSON的形式返回结果
public class WebController {

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public MyUser getUser() {
        MyUser myUser = new MyUser();
        myUser.setName("小明");
        myUser.setAge(12);
        myUser.setPass("123456");
        return myUser;
    }

    @RequestMapping("/getUsers")
    public List<MyUser> getusers() {
        List<MyUser> users = new ArrayList<>();

        MyUser user1 = new MyUser();
        user1.setName("abc");
        user1.setAge(13);
        user1.setPass("abc13");

        MyUser user2 = new MyUser();
        user2.setName("def");
        user2.setAge(14);
        user2.setPass("def14");

        users.add(user1);
        users.add(user2);
        return users;
    }

    @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
    public String get(@PathVariable String name) {
        return name;
    }

    @RequestMapping("/saveUser")
    public void saveUser(@Valid MyUser user, BindingResult result) {
        System.out.println("user:" + user);
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
    }
}
