package com.review.rustfulcrud.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取默认的地区和语言
        Locale locale = Locale.getDefault();
        //从页面中获取参数
        String l = httpServletRequest.getParameter("l");
        //如果存在参数就把默认的地区和语言修改了，不存在则使用默认的
        if(!StringUtils.isEmpty(l)){
            //分割字符串 zh_CN
            String[] ls = l.split("_");
            locale = new Locale(ls[0],ls[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
    }
}
