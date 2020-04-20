package com.review.rustfulcrud.config;

import com.review.rustfulcrud.bean.User;
import com.review.rustfulcrud.servlet.UserServlet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserServlet userServlet;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("———————————————————授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        info.addStringPermission(user.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("———————————————————认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        token.isRememberMe();
        User user = userServlet.getUser(token.getUsername());
        if (user==null){
            return null;
        }

        return new SimpleAuthenticationInfo(user,user.getPassWord(),"");
    }
}
