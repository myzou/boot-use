package com.example.boot.resource.controller;

import com.example.boot.resource.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContoller {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public Object hello() {
        return "hello springboot~~";
    }

    @RequestMapping("/getResource")
    public Object getResource() {
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);
        return bean;
    }

}
