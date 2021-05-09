package com.squirrel.jpa.repository;


import com.squirrel.jpa.model.MyUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb",
                userRepository.findByUserNameOrEmail("b", "b@gmail.com").getNickName());
        userRepository.delete(userRepository.findByUserName("c"));
    }

    @Test
    public void testPageQuery() {
        int page = 1, size = 2;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        userRepository.findAll(pageable);
        userRepository.findByNickName("aa", pageable);
        userRepository.queryFirst10ByUserName("a", pageable);
    }
}
