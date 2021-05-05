package com.squirrel.mybatisxml;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages =
        {"com.squirrel.mybatisxml.mapper.one", "com.squirrel.mybatisxml.mapper.two"})
public class MyBatisXmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisXmlApplication.class, args);
    }

}
