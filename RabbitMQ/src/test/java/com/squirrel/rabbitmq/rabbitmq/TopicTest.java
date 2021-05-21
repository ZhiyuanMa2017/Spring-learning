package com.squirrel.rabbitmq.rabbitmq;

import com.squirrel.rabbitmq.rabbit.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void topic() throws InterruptedException {
        topicSender.send();
        Thread.sleep(1000L);
    }

    @Test
    public void topic1() throws InterruptedException {
        topicSender.send1();
        Thread.sleep(1000L);
    }

    @Test
    public void topic2() throws InterruptedException {
        topicSender.send2();
        Thread.sleep(1000L);
    }
}
