package com.springboot.shiro.bean;

import lombok.Data;

/**
 * @author dyz
 * @program boot-use
 */
@Data
public class User {

    private Integer id;

    private String name;

    private String password;

    private String perms;

}
