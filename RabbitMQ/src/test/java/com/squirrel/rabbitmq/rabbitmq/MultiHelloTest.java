package com.squirrel.rabbitmq.rabbitmq;

import com.squirrel.rabbitmq.rabbit.multihello.MHSender;
import com.squirrel.rabbitmq.rabbit.multihello.MHSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiHelloTest {

    @Autowired
    private MHSender mhSender;

    @Autowired
    private MHSender2 mhSender2;

    @Test
    public void oneToMany() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            mhSender.send(i);
        }
        Thread.sleep(10000L);
    }

    @Test
    public void manyToMany() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            mhSender.send(i);
            mhSender2.send(i);
        }
        Thread.sleep(10000L);

    }
}
