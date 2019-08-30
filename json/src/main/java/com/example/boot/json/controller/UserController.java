package com.example.boot.json.controller;

import com.example.boot.json.pojo.User;
import com.example.boot.json.pojo.WebJsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    @RequestMapping("/getUser")
    public User getUser() {
        User u = new User();
        u.setName("dyz");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPassword("dyz");
        u.setDesc("dyz");
        return u;
    }

    @RequestMapping("/getUserJson")
    public WebJsonResult getUserJson() {
        User u = new User();
        u.setName("dyz");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPassword("dyz");
        u.setDesc("dyz");
        return WebJsonResult.ok(u);
    }

}
