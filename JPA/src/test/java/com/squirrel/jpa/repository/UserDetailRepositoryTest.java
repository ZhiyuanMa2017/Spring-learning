package com.squirrel.jpa.repository;

import com.squirrel.jpa.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailRepositoryTest {

    @Resource
    UserDetailRepository userDetailRepository;

    @Test
    public void testUserInfo() {
        List<UserInfo> userInfoList = userDetailRepository.findUserInfo("swim");
        for (UserInfo info : userInfoList) {
            System.out.println("userInfo: " + info.getUserName()
                    + " " + info.getEmail() + " "
                    + info.getHobby() + " " + info.getIntroduction());
        }
    }
}
