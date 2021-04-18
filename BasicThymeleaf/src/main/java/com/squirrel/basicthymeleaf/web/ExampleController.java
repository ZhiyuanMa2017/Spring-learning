package com.squirrel.basicthymeleaf.web;

import com.squirrel.basicthymeleaf.model.MyUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ExampleController {

    @RequestMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "squirrel");
        return "string";
    }

    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("ganname", "goodfeli");
        map.addAttribute("ssname", "clowwindy");
        map.addAttribute("img",
                "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ahri_15.jpg");
        return "url";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "zhangsan");
        map.addAttribute("age", 50);
        map.addAttribute("flag", "yes");
        return "eq";
    }

    @RequestMapping("/switch")
    public String switchExample(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }

    private List<MyUser> getUserList() {
        MyUser user1 = new MyUser("a", 10, "123");
        MyUser user2 = new MyUser("b", 11, "456");
        MyUser user3 = new MyUser("c", 12, "789");
        return new ArrayList<>(Arrays.asList(user1, user2, user3));
    }
}
