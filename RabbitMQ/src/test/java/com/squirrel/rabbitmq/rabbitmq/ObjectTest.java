package com.squirrel.rabbitmq.rabbitmq;

import com.squirrel.rabbitmq.model.User;
import com.squirrel.rabbitmq.rabbit.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTest {

    @Autowired
    private ObjectSender objectSender;

    @Test
    public void sendObject() throws InterruptedException {
        User user = new User();
        user.setName("squirrel");
        user.setPass("123456");
        objectSender.send(user);
        Thread.sleep(1000L);
    }
}
