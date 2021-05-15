package com.squirrel.druid.web;

import com.squirrel.druid.mapper.test1.User1Mapper;
import com.squirrel.druid.mapper.test2.User2Mapper;
import com.squirrel.druid.model.MyUser;
import com.squirrel.druid.page.Page;
import com.squirrel.druid.param.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;


    @RequestMapping("/getUsers")
    public List<MyUser> getUsers() {
        List<MyUser> usersOne = user1Mapper.getAll();
        List<MyUser> usersTwo = user2Mapper.getAll();
        usersOne.addAll(usersTwo);
        return usersOne;
    }

    @RequestMapping("/getList")
    public Page<MyUser> getList(UserParam userParam) {
        List<MyUser> users = user1Mapper.getList(userParam);
        long count = user1Mapper.getCount(userParam);
        return new Page(userParam, count, users);
    }

    @RequestMapping("/getUser")
    public MyUser getUser(Long id) {
        return user1Mapper.getOne(id);
    }

    @RequestMapping("/add")
    public void save(MyUser myUser) {
        user1Mapper.insert(myUser);
    }

    @RequestMapping(value = "update")
    public void update(MyUser myUser) {
        user1Mapper.update(myUser);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }


}
