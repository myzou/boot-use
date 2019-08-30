package com.springboot.shiro.service.impl;

import com.springboot.shiro.bean.User;
import com.springboot.shiro.mapper.UserMapper;
import com.springboot.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dyz
 * @program boot-use
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

}
