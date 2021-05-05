package com.squirrel.mybatisxml.mapper;

import com.squirrel.mybatisxml.enums.UserSexEnum;
import com.squirrel.mybatisxml.mapper.one.User1Mapper;
import com.squirrel.mybatisxml.mapper.two.User2Mapper;
import com.squirrel.mybatisxml.model.MyUser;
import com.squirrel.mybatisxml.page.Page;
import com.squirrel.mybatisxml.param.UserParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserMapperTest {

    @Autowired
    private User1Mapper userMapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testInsertTwo() throws Exception {
        userMapper.insert(new MyUser("c", "123", UserSexEnum.MAN));
        user2Mapper.insert(new MyUser("d", "123", UserSexEnum.MAN));
        user2Mapper.insert(new MyUser("e", "123", UserSexEnum.MAN));

    }


    @Test
    public void testUser() {
        userMapper.insert(new MyUser("a", "123", UserSexEnum.MAN));
        int count = userMapper.delete(2L);
        MyUser myUser = userMapper.getOne(1L);
        myUser.setNickName("b");
        userMapper.update(myUser);
        List<MyUser> users = userMapper.getAll();
    }

    @Test
    public void testInsert() {
        userMapper.insert(new MyUser("a", "123", UserSexEnum.MAN));
        userMapper.insert(new MyUser("b", "1234", UserSexEnum.WOMAN));
        userMapper.insert(new MyUser("c", "12345", UserSexEnum.MAN));

        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() {
        List<MyUser> users = userMapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.toString());
        }
    }

    @Test
    public void testUpdate() {
        long id = 2L;
        MyUser myUser = userMapper.getOne(id);
        if (myUser != null) {
            System.out.println(myUser.toString());
            myUser.setNickName("abc");
            userMapper.update(myUser);
            Assert.assertTrue(("abc".equals(userMapper.getOne(id).getNickName())));
            System.out.println(myUser.toString());
        } else {
            System.out.println("not found");
        }
    }

    @Test
    public void testDelete() {
        int count = userMapper.delete(4L);
        if (count > 0) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
    }

    @Test
    public void testPage() {
        UserParam userParam = new UserParam();
        userParam.setCurrentPage(0);
        List<MyUser> users = userMapper.getList(userParam);
        long count = userMapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        System.out.println("page: " + page);
    }
}
