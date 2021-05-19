package com.squirrel.activemq.send;

import com.squirrel.activemq.producer.Producer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleActiveMqTests {

    @Autowired
    private Producer producer;

    @Rule
    public OutputCaptureRule output = new OutputCaptureRule();

    @Test
    public void sendSimpleQueueMessage() throws InterruptedException {
        this.producer.sendQueue("Test queue message");
        Thread.sleep(1000L);
        assertThat(output).contains("Test queue");

        for (int i = 0; i < 100; i++) {
            this.producer.sendQueue("Test queue message " + i);
        }
        Thread.sleep(1000L);
    }

    @Test
    public void sendSimpleTopicMessage() throws InterruptedException {
        this.producer.sendTopic("Test topic message");
        Thread.sleep(1000L);
    }
}
