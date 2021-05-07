package com.squirrel.mybatisannotations.web;

import com.squirrel.mybatisannotations.mapper.test1.User1Mapper;
import com.squirrel.mybatisannotations.model.MyUser;
import com.squirrel.mybatisannotations.page.Page;
import com.squirrel.mybatisannotations.param.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private User1Mapper user1Mapper;


    @RequestMapping("/getUsers")
    public List<MyUser> getUsers() {
        return user1Mapper.getAll();
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
