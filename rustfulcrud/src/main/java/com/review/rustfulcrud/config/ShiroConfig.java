package com.review.rustfulcrud.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("userRealm")
    public UserRealm userRealm(){
      return   new UserRealm();
    }
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")
                                                                 DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        //过滤
        Map<String, String> map = new HashMap<>();
        /**
         * anon 无需认证
         * authc 需要认证
         * user 需要记住我功能
         * perms 对某个资源有访问权限
         * role 需要某个角色才可以访问
         */
        map.put("/","anon");
        map.put("/main.html","authc");
        map.put("/emp","authc");
        map.put("/dashboard","authc");

        map.put("/addEmpPage","perms[user:all]");
        map.put("/EmpSubmit","perms[user:all]");
        map.put("/deleteEmpByEid","perms[user:all]");
        map.put("/toEmpPageEdit/*","perms[user:all]");
        map.put("/EmpSubmit","perms[user:all]");
        //设置登录url
        bean.setLoginUrl("/");
        //登录成功url
        bean.setSuccessUrl("/login");
        //未授权url
        bean.setUnauthorizedUrl("/toUnauthorized");
        //注销url
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
