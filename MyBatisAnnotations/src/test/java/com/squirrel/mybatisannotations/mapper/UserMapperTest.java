package com.squirrel.mybatisannotations.mapper;

import com.squirrel.mybatisannotations.enums.UserSexEnum;
import com.squirrel.mybatisannotations.mapper.test1.User1Mapper;
import com.squirrel.mybatisannotations.mapper.test2.User2Mapper;
import com.squirrel.mybatisannotations.model.MyUser;
import com.squirrel.mybatisannotations.page.Page;
import com.squirrel.mybatisannotations.param.UserParam;
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
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testInsertTwo() throws Exception {
        user1Mapper.insert(new MyUser("c", "123", UserSexEnum.MAN));
        user2Mapper.insert(new MyUser("d", "123", UserSexEnum.MAN));
        user2Mapper.insert(new MyUser("e", "123", UserSexEnum.MAN));

    }


    @Test
    public void testUser() {
        user1Mapper.insert(new MyUser("a", "123", UserSexEnum.MAN));
        int count = user1Mapper.delete(2L);
        MyUser myUser = user1Mapper.getOne(1L);
        myUser.setNickName("b");
        user1Mapper.update(myUser);
        List<MyUser> users = user1Mapper.getAll();
    }

    @Test
    public void testInsert() {
        user1Mapper.insert(new MyUser("a", "123", UserSexEnum.MAN));
        user1Mapper.insert(new MyUser("b", "1234", UserSexEnum.WOMAN));
        user1Mapper.insert(new MyUser("c", "12345", UserSexEnum.MAN));

        Assert.assertEquals(3, user1Mapper.getAll().size());
    }

    @Test
    public void testQuery() {
        List<MyUser> users = user1Mapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.toString());
        }
    }

    @Test
    public void testUpdate() {
        long id = 2L;
        MyUser myUser = user1Mapper.getOne(id);
        if (myUser != null) {
            System.out.println(myUser.toString());
            myUser.setNickName("abc");
            user1Mapper.update(myUser);
            Assert.assertTrue(("abc".equals(user1Mapper.getOne(id).getNickName())));
            System.out.println(myUser.toString());
        } else {
            System.out.println("not found");
        }
    }

    @Test
    public void testDelete() {
        int count = user1Mapper.delete(4L);
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
        List<MyUser> users = user1Mapper.getList(userParam);
        long count = user1Mapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        System.out.println("page: " + page);
    }
}
