package com.squirrel.druidjpa.web;

import com.squirrel.druidjpa.model.MyUser;
import com.squirrel.druidjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUsers")
    public List<MyUser> getUsers() {
        List<MyUser> userList = userRepository.findAll();
        return userList;
    }
}
