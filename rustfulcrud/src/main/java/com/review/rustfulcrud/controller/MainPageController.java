package com.review.rustfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainPageController {

    @GetMapping(path = "/dashboard")
    public String dashboard(HttpSession session){
        session.setAttribute("mai_body_name","main_body_dashboard");
        return "redirect:main.html";
    }
}
