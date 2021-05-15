package com.squirrel.druid.mapper;

import com.squirrel.druid.enums.UserSexEnum;
import com.squirrel.druid.mapper.test1.User1Mapper;
import com.squirrel.druid.mapper.test2.User2Mapper;
import com.squirrel.druid.model.MyUser;
import com.squirrel.druid.page.Page;
import com.squirrel.druid.param.UserParam;
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
        user1Mapper.insert(new MyUser("a", "123", UserSexEnum.MAN));
        user2Mapper.insert(new MyUser("b", "456", UserSexEnum.WOMAN));
        user2Mapper.insert(new MyUser("c", "789", UserSexEnum.MAN));
    }

    @Test
    public void testQuery() {
        List<MyUser> users = user2Mapper.getAll();
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
    public void testPage() {
        UserParam userParam = new UserParam();
        userParam.setUserSex("WOMAN");
        userParam.setCurrentPage(0);
        List<MyUser> users = user1Mapper.getList(userParam);
        long count = user1Mapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        System.out.println("page: " + page);
    }
}
