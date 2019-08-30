package com.springboot.shiro.service;

import com.springboot.shiro.bean.User;

/**
 * @author dyz
 * @program boot-use
 */
public interface UserService {

    User findByName(String name);

    User findById(Integer id);

}
