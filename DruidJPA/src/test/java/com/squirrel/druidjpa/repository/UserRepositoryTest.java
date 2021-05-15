package com.squirrel.druidjpa.repository;


import com.squirrel.druidjpa.model.MyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new MyUser("a", "123", "a@gmail.com", "aa", formattedDate));
        userRepository.save(new MyUser("b", "456", "b@gmail.com", "bb", formattedDate));
        userRepository.save(new MyUser("c", "789", "c@gmail.com", "cc", formattedDate));
    }
}
