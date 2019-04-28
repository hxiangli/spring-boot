package com.hlfc.springboot.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 登录成功页
     * @return
     */
    @RequestMapping("/index")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登录用户"+name);
        return "login/home";
    }

}