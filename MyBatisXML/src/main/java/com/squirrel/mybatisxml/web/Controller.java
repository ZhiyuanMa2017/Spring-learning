package com.squirrel.mybatisxml.web;

import com.squirrel.mybatisxml.mapper.one.User1Mapper;
import com.squirrel.mybatisxml.mapper.two.User2Mapper;
import com.squirrel.mybatisxml.model.MyUser;
import com.squirrel.mybatisxml.page.Page;
import com.squirrel.mybatisxml.param.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private User1Mapper userMapper;

    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/getUsers")
    public List<MyUser> getUsers() {
        return userMapper.getAll();
    }

    @RequestMapping("/getList")
    public Page<MyUser> getList(UserParam userParam) {
        List<MyUser> users = userMapper.getList(userParam);
        long count = userMapper.getCount(userParam);
        return new Page(userParam, count, users);
    }

    @RequestMapping("/getUser")
    public MyUser getUser(Long id) {
        return userMapper.getOne(id);
    }

    @RequestMapping("/add")
    public void save(MyUser myUser) {
        userMapper.insert(myUser);
    }

    @RequestMapping(value = "update")
    public void update(MyUser myUser) {
        userMapper.update(myUser);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }


}
