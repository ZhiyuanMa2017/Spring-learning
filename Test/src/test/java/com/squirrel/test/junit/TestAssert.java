package com.squirrel.test.junit;

import com.squirrel.test.util.Calculation;
import org.junit.Test;


import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestAssert {

    @Test
    public void test() {

        int res = new Calculation().add(1, 1);

        assertThat(res, allOf(greaterThan(1), lessThan(3)));
        assertThat(res, anyOf(greaterThan(1), lessThan(1)));
        assertThat(res, anything());
        assertThat(res, is(2));
        assertThat(res, not(1));

        String n = new Calculation().getName("abc");

        assertThat(n, containsString("bc"));
        assertThat(n, startsWith("ab"));
        assertThat(n, endsWith("bc"));
        assertThat(n, equalTo("abc"));
        assertThat(n, equalToIgnoringCase("abc"));
        assertThat(n, equalToCompressingWhiteSpace("abc "));

        List<String> list = new Calculation().getList("abc");
        assertThat(list, hasItem("abc"));

        Map<String, String> m = new Calculation().getMap("abc", "def");
        assertThat(m, hasEntry("abc", "def"));
        assertThat(m, hasKey("abc"));
        assertThat(m, hasValue("def"));
    }
}
