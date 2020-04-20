package com.review.rustfulcrud.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyLoginHandlerInterceptor implements HandlerInterceptor {
    //执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         Object userInfo = request.getSession().getAttribute("userInfo");
        if(userInfo!=null){
            return true;
        }else{
            request.setAttribute("msg","您还没有登录");
            /**
             * 这里的请求转发  /lonin.html 需要在webMvc映射中有
             * registry.addViewController("/login.html").setViewName("login");
             * 否则404
             */
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }

    }
    //执行之中
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    //执行后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
