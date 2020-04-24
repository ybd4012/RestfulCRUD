package com.review.rustfulcrud.config;

import com.review.rustfulcrud.component.MyLocaleResolver;
import com.review.rustfulcrud.component.MyLoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/aaa.html").setViewName("login");
        //登录成功后 重定向到main.html 利用thymeleaf模板解析器 解析到templates下的main.html页面
        registry.addViewController("/main.html").setViewName("main");
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyLoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/","/login","/login.html","/asserts/**","/webjars/**");
//        //  "/druid/*"???
//    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new MyMvcConfig();
    }
    //将组件加入到容器中
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}
