package com.squirrel.multimongodb.repository;

import com.squirrel.multimongodb.model.User;
import com.squirrel.multimongodb.repository.primary.PrimaryRepository;
import com.squirrel.multimongodb.repository.secondary.SecondaryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiTest {

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    @Test
    public void testSave() {
        this.primaryRepository.save(new User(1L, "abc", "123"));
        this.primaryRepository.save(new User(2L, "bcd", "123"));
        this.secondaryRepository.save(new User(1L, "def", "456"));
        List<User> list1 = this.primaryRepository.findAll();
        for (User user : list1) {
            System.out.println(user.toString());
        }
        List<User> list2 = this.secondaryRepository.findAll();
        for (User user : list2) {
            System.out.println(user.toString());
        }
    }
}
