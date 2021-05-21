package com.squirrel.mongodb.TemplateRepository;

import com.squirrel.mongodb.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateUserRepositoryTest {

    @Autowired
    private TemplateUserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User();
        user.setId(2L);
        user.setUserName("abc");
        user.setPassWord("123456");
        userRepository.saveUser(user);
    }

    @Test
    public void testFindByUsername() {
        User user = userRepository.findUserByUserName("abc");
        System.out.println("User: " + user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2L);
        user.setUserName("def");
        user.setPassWord("654321");
        userRepository.updateUser(user);
    }

    @Test
    public void testDelete() {
        userRepository.deleteUserById(1L);
    }

}
