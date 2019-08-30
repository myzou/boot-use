package com.example.boot.mybatis.controller;

import com.example.boot.mybatis.pojo.SysUser;
import com.example.boot.mybatis.pojo.WebJsonResult;
import com.example.boot.mybatis.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sid sid;

    @RequestMapping("/saveUser")
    public WebJsonResult saveUser() throws Exception {
        String userId = sid.nextShort();
        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("dyz" + new Date());
        user.setNickname("dyz" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        userService.saveUser(user);
        return WebJsonResult.ok("保存成功");
    }

    @RequestMapping("/updateUser")
    public WebJsonResult updateUser() {
        SysUser user = new SysUser();
        user.setId("1001");
        user.setUsername("1001-updated" + new Date());
        user.setNickname("1001-updated" + new Date());
        user.setPassword("1001-updated");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        userService.updateUser(user);
        return WebJsonResult.ok("保存成功");
    }

    @RequestMapping("/deleteUser")
    public WebJsonResult deleteUser(String userId) {
        userService.deleteUser(userId);
        return WebJsonResult.ok("删除成功");
    }

    @RequestMapping("/queryUserById")
    public WebJsonResult queryUserById(String userId) {
        return WebJsonResult.ok(userService.queryUserById(userId));
    }

    @RequestMapping("/queryUserList")
    public WebJsonResult queryUserList() {
        SysUser user = new SysUser();
        user.setUsername("dyz");
        user.setNickname("dyz");
        List<SysUser> userList = userService.queryUserList(user);
        return WebJsonResult.ok(userList);
    }

    @RequestMapping("/queryUserListPaged")
    public WebJsonResult queryUserListPaged(Integer page) {
        if (page == null) {
            page = 1;
        }
        int pageSize = 10;
        SysUser user = new SysUser();
//		user.setNickname("lee");
        List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);
        return WebJsonResult.ok(userList);
    }

    @RequestMapping("/queryUserByIdCustom")
    public WebJsonResult queryUserByIdCustom(String userId) {
        return WebJsonResult.ok(userService.queryUserByIdCustom(userId));
    }

    @RequestMapping("/saveUserTransactional")
    public WebJsonResult saveUserTransactional() {
        String userId = sid.nextShort();
        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("lee" + new Date());
        user.setNickname("lee" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        userService.saveUserTransactional(user);
        return WebJsonResult.ok("保存成功");
    }
}
