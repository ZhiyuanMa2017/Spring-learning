package com.squirrel.test.service.impl;

import com.squirrel.test.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("Hello Service");
    }
}
