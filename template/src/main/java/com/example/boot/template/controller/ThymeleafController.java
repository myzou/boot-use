package com.example.boot.template.controller;

import com.example.boot.template.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dyz
 */
@Controller
@RequestMapping("th")
public class ThymeleafController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "thymeleaf-index");
        return "thymeleaf/index";
    }

    @RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("test")
    public String test(ModelMap map) {
        User u = new User();
        u.setName("dyz");
        u.setAge(10);
        u.setPassword("123465");
        u.setBirthday(new Date());
        u.setDesc("<font color='green'><b>hello dyz</b></font>");
        map.addAttribute("user", u);

        User u1 = new User();
        u1.setAge(19);
        u1.setName("dyz1");
        u1.setPassword("123456");
        u1.setBirthday(new Date());

        User u2 = new User();
        u2.setAge(17);
        u2.setName("dyz2");
        u2.setPassword("123456");
        u2.setBirthday(new Date());

        List<User> userList = new ArrayList<>();
        userList.add(u);
        userList.add(u1);
        userList.add(u2);

        map.addAttribute("userList", userList);

        return "thymeleaf/test";
    }

    @PostMapping("postForm")
    public String postForm(User u) {
        System.out.println("姓名：" + u.getName());
        System.out.println("年龄：" + u.getAge());
        return "redirect:/th/test";
    }

    @RequestMapping("error")
    public String error(User u) {
        int a = 1 / 0;
        return "redirect:/th/test";
    }

}
