package com.squirrel.multijpa.repository;


import com.squirrel.multijpa.model.MyUser;
import com.squirrel.multijpa.repository.test1.UserTest1Repository;
import com.squirrel.multijpa.repository.test2.UserTest2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserTest1Repository userTest1Repository;

    @Resource
    private UserTest2Repository userTest2Repository;

    @Test
    public void testSave() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userTest1Repository.save(new MyUser("a", "123", "a@gmail.com", "aa", formattedDate));
        userTest1Repository.save(new MyUser("b", "456", "b@gmail.com", "bb", formattedDate));
        userTest2Repository.save(new MyUser("c", "789", "c@gmail.com", "cc", formattedDate));
        userTest2Repository.save(new MyUser("d", "101", "d@gmail.com", "dd", formattedDate));

    }

    @Test
    public void testDelete() {
        userTest1Repository.deleteAll();
        userTest2Repository.deleteAll();
    }

    @Test
    public void testBaseQuery() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        MyUser myUser = new MyUser("e", "098", "e@gmail.com", "ee", formattedDate);

        userTest1Repository.findAll();
        System.out.println(userTest1Repository.findById(1L).toString());
        userTest1Repository.save(myUser);

        myUser.setId(3L);

        userTest1Repository.delete(myUser);
        System.out.println(userTest1Repository.count());
        System.out.println(userTest1Repository.findById(2L).toString());
    }

}
