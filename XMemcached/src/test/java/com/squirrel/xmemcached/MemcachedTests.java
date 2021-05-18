package com.squirrel.xmemcached;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetSocketAddress;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTests {

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void testGetSet() throws Exception {
        memcachedClient.set("hello", 0, "hello, Xmemcached");
        String value = memcachedClient.get("hello");
        System.out.println("hello = " + value);
        memcachedClient.delete("hello");
    }

    @Test
    public void testMore() throws Exception {
        if (!memcachedClient.set("hello", 0, "world")) {
            System.out.println("set error");
        }
        if (!memcachedClient.add("hello", 0, "abc")) {
            System.out.println("add error");
        }
        if (!memcachedClient.replace("hello", 0, "bcd")) {
            System.out.println("replace error");
        }
        memcachedClient.append("hello", " good");
        memcachedClient.prepend("hello", "h ");
        String name = memcachedClient.get("hello", new StringTranscoder());
        System.out.println(name);
        memcachedClient.deleteWithNoReply("hello");
    }

    @Test
    public void testIncrDecr() throws Exception {
        memcachedClient.delete("Incr");
        memcachedClient.delete("Decr");
        System.out.println(memcachedClient.incr("Incr", 6, 12));
        System.out.println(memcachedClient.incr("Incr", 3));
        System.out.println(memcachedClient.incr("Incr", 2));
        System.out.println(memcachedClient.decr("Decr", 1, 6));
        System.out.println(memcachedClient.decr("Decr", 2));
    }

    @Test
    public void testCounter() throws Exception {
        Counter counter = memcachedClient.getCounter("counter", 10);
        System.out.println("counter = " + counter.get());
        long c1 = counter.incrementAndGet();
        System.out.println(c1);
        long c2 = counter.decrementAndGet();
        System.out.println(c2);
        long c3 = counter.addAndGet(-10);
        System.out.println(c3);
    }

    @Test
    public void testCas() throws Exception {
        memcachedClient.set("cas", 0, 100);
        GetsResponse<Integer> response = memcachedClient.gets("cas");
        System.out.println("response value " + response.getValue());

        long cas = response.getCas();
        if (!memcachedClient.cas("cas", 0, 200, cas)) {
            System.err.println("cas error");
        }
        System.out.println("cas value " + memcachedClient.get("cas"));

        memcachedClient.cas("cas", 0, new CASOperation<Integer>() {
            public int getMaxTries() {
                return 1;
            }

            public Integer getNewValue(long currentCAS, Integer currentValue) {
                return 300;
            }
        });
        System.out.println("cas value " + memcachedClient.get("cas"));
    }

    @Test
    public void testTouch() throws Exception {
        memcachedClient.set("Touch", 2, "Touch Value");
        Thread.sleep(1000);
        memcachedClient.touch("Touch", 6);
        Thread.sleep(2000);
        String value = memcachedClient.get("Touch");
        System.out.println("Touch = " + value);
    }

    @Test
    public void testStat() throws Exception {
        Map<InetSocketAddress, Map<String, String>> result = memcachedClient.getStats();
        System.out.println("Stats = " + result.toString());

        Map<InetSocketAddress, Map<String, String>> items = memcachedClient.getStatsByItem("items");
        System.out.println("Items = " + items);

    }
}
