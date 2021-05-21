package com.squirrel.mongodb.repository;

import com.squirrel.mongodb.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        for (long i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("abc" + i);
            user.setPassWord("123456");
            userRepository.save(user);
        }
    }

    @Test
    public void testFindByUsername() {
        User user = userRepository.findByUserName("abc1");
        System.out.println("User: " + user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2L);
        user.setUserName("def");
        user.setPassWord("654321");
        userRepository.save(user);
    }

    @Test
    public void testDelete() {
        userRepository.deleteById(1L);
    }

    @Test
    public void testPage() {
        Pageable pageable = PageRequest.of(2, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        System.out.println("Users: " + page.getContent());
    }
}
