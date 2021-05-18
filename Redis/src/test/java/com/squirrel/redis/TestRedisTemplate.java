package com.squirrel.redis;

import com.squirrel.redis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("squirrel", "first");
        Assert.assertEquals("first", redisTemplate.opsForValue().get("squirrel"));
    }

    @Test
    public void testObj() {
        User user = new User("squirrel", "123", "s@gmail.com", "abc", "2021");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.squirrel", user);
        User user1 = operations.get("com.squirrel");
        System.out.println("User: " + user1.toString());
    }

    @Test
    public void testExpire() throws Exception {
        User user = new User("squirrel", "123", "s@gmail.com", "abc", "2021");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("expire", user, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("expire");
        if (exists) {
            System.out.println("exists");
        } else {
            System.out.println("not exists");
        }
    }

    @Test
    public void testDelete() {
        redisTemplate.opsForValue().set("deleteKey", "squirrel");
        redisTemplate.delete("deleteKey");
        boolean exists = redisTemplate.hasKey("deleteKey");
        if (exists) {
            System.out.println("exists");
        } else {
            System.out.println("not exists");
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("hash", "k", "v");
        String value = (String) hashOperations.get("hash", "k");
        System.out.println("hash value: " + value);
    }

    @Test
    public void testList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list", "first");
        listOperations.leftPush("list", "second");
        listOperations.leftPush("list", "third");
        String value = listOperations.leftPop("list");
        System.out.println("list value: " + value);

        List<String> values = listOperations.range("list", 0, 1);
        for (String s : values) {
            System.out.println("list range value: " + s);
        }
    }

    @Test
    public void testSet() {
        String key = "set";
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add(key, "one");
        setOperations.add(key, "one");
        setOperations.add(key, "two");
        setOperations.add(key, "three");
        Set<String> values = setOperations.members(key);
        for (String value : values) {
            System.out.println("set value: " + value);
        }
    }

    @Test
    public void testSetMore() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        String key1 = "set1";
        String key2 = "set2";
        setOperations.add(key1, "one");
        setOperations.add(key1, "two");
        setOperations.add(key1, "three");
        setOperations.add(key2, "one");
        setOperations.add(key2, "one");
        setOperations.add(key2, "four");
        Set<String> diffs = setOperations.difference(key1, key2);
        for (String diff : diffs) {
            System.out.println("set diff value: " + diff);
        }
        Set<String> unions = setOperations.union(key1, key2);
        for (String union : unions) {
            System.out.println("set union value: " + union);
        }
    }

    @Test
    public void testZSet() {
        String key = "ZSet";
        redisTemplate.delete(key);
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(key, "one", 1);
        zSetOperations.add(key, "two", 22);
        zSetOperations.add(key, "three", 3);
        zSetOperations.add(key, "four", 4);
        Set<String> zSets = zSetOperations.range(key, 0, 3);
        for (String s : zSets) {
            System.out.println("zSet value: " + s);
        }
        Set<String> zSetByScore = zSetOperations.rangeByScore(key, 0, 3);
        for (String s : zSetByScore) {
            System.out.println("zSet ByScore value: " + s);
        }
    }

}
