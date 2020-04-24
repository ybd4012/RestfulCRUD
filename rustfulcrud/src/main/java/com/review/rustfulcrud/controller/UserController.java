package com.review.rustfulcrud.controller;

import com.review.rustfulcrud.bean.User;
import com.review.rustfulcrud.servlet.UserServlet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 页面包含user→ login____logout
 */
@Controller
public class UserController {
    @Autowired
    UserServlet userServlet;

    /**
     * @return
     */
    @PostMapping(path = "/login")
    public String login(User user, Model model, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("userInfo",token.getUsername());
            session.setAttribute("mai_body_name","main_body_dashboard");
            return "redirect:/main.html";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","username does is exist");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "password error");
            return "login";
        }
    }
    @GetMapping(path = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userInfo");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login.html";
    }
    @RequestMapping("/toUnauthorized")
    @ResponseBody
    public String toUnauthorized(){
        return "to Unauthorized";
    }

}
