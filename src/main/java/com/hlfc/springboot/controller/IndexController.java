package com.hlfc.springboot.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *首页以及.html页面跳转
 * @Author hxl
 * @Date  2019/6/13
 **/
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
        return "index";
    }

    /**
     * 配置 .html 后缀的请求
     * @return
     */
    @RequestMapping("/**/*.html")
    public String commonhtml(HttpServletRequest request) {

        //获取路径
        String url = request.getRequestURI();

        System.out.println("请求地址："+url);


        return url.substring(0, url.length()-5);
    }

}