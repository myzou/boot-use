package com.example.boot.mybatis.mapper;

import com.example.boot.mybatis.pojo.SysUser;

import java.util.List;

public interface SysUserMapperCustom {

    List<SysUser> queryUserSimplyInfoById(String id);
}
