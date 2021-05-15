package com.squirrel.druidmultijpa.web;

import com.squirrel.druidmultijpa.model.MyUser;
import com.squirrel.druidmultijpa.repository.test1.UserTest1Repository;
import com.squirrel.druidmultijpa.repository.test2.UserTest2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserTest1Repository userTest1Repository;

    @Autowired
    private UserTest2Repository userTest2Repository;

    @RequestMapping("/getUsers")
    public List<MyUser> getUsers() {
        List<MyUser> list1 = userTest1Repository.findAll();
        List<MyUser> list2 = userTest2Repository.findAll();
        list1.addAll(list2);
        return list1;
    }
}
