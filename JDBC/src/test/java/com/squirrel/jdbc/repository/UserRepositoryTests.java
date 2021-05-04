package com.squirrel.jdbc.repository;

import com.squirrel.jdbc.model.MyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;


    @Test
    public void testSave() {
        MyUser myUser = new MyUser("squirrel", "123", 12);
        userRepository.save(myUser, primaryJdbcTemplate);
        userRepository.save(myUser, secondaryJdbcTemplate);
    }

//    @Test
//    public void testUpdate() {
//        MyUser myUser = new MyUser("squirrel", "123", 13);
//        myUser.setId(1L);
//        userRepository.update(myUser);
//    }
//
//    @Test
//    public void testDelete() {
//        userRepository.delete(1L);
//    }
//
//    @Test
//    public void testQueryOne() {
//        MyUser myUser = userRepository.findById(2L);
//        System.out.println("MyUser == " + myUser.toString());
//    }
//
//    @Test
//    public void testQueryAll() {
//        List<MyUser> myUsers = userRepository.findAll();
//        for (MyUser myUser : myUsers) {
//            System.out.println("MyUser == " + myUser.toString());
//        }
//    }
}
