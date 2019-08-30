package com.springboot.shiro.mapper;

import com.springboot.shiro.bean.User;

/**
 * @author dyz
 * @program boot-use
 */
public interface UserMapper {

    User findByName(String name);

    User findById(Integer id);

}
