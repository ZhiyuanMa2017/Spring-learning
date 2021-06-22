package com.squirrel.test.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Calculation {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int div(int a, int b) {
        return a / b;
    }

    public String getName(String name) {
        return name;
    }

    public List<String> getList(String item) {
        List<String> list = new ArrayList<>();
        list.add(item);
        return list;
    }

    public Map<String, String> getMap(String key, String value) {
        Map<String, String> m = new HashMap<>();
        m.put(key, value);
        return m;
    }
}
