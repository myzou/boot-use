package com.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dyz
 * @program boot-use
 */
@Controller
public class UserController {

    /**
     * 测试方法
     */
    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "ok";
    }

    @RequestMapping("/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login";
    }

    /**
     * 未授权页面
     */
    @RequestMapping("/noAuth")
    public String noAuth() {
        return "/noAuth";
    }

    /**
     * 测试thymeleaf
     */
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("name", "程序员");
        return "test";
    }

    /**
     * 登录逻辑处理
     */
    @RequestMapping("/login")
    public String login(String name, String password, Model model) {
        System.out.println("name=" + name);
        /*
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3.执行登录方法
        try {
            subject.login(token);

            //登录成功
            //跳转到test.html
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

}
