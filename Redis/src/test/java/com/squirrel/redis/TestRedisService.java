package com.squirrel.redis;

import com.squirrel.redis.model.User;
import com.squirrel.redis.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisService {

    @Autowired
    private RedisService redisService;

    @Test
    public void testString() {
        redisService.set("squirrel", "one");
        Assert.assertEquals("one", redisService.get("squirrel"));
    }

    @Test
    public void testObj() {
        User user = new User("squirrel", "123", "s@gmail.com", "abc", "2021");
        redisService.set("user", user);
        User user1 = (User) redisService.get("user");
        System.out.println("User: " + user1.toString());
    }
}
