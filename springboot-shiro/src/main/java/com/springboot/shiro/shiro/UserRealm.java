package com.springboot.shiro.shiro;

import com.springboot.shiro.bean.User;
import com.springboot.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * 自定义Realm
 *
 * @author dyz
 * @program boot-use
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userSerivce;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        //给资源进行授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //authorizationInfo.addStringPermission("user:add");

        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User dbUser = userSerivce.findById(user.getId());

        authorizationInfo.addStringPermission(dbUser.getPerms());

        return authorizationInfo;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userSerivce.findByName(token.getUsername());

        if (user == null) {
            //用户名不存在
            //shiro底层会抛出UnKnowAccountException
            return null;
        }

        //2.判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
