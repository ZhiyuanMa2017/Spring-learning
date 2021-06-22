package com.squirrel.test.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

    @Rule
    public OutputCaptureRule outputCaptureRule = new OutputCaptureRule();
    @Resource
    HelloService helloService;

    @Test
    public void sayHelloTest() {
        helloService.sayHello();
        assertThat(this.outputCaptureRule.toString().contains("Hello Service")).isTrue();
    }
}
