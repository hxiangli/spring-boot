package com.hlfc.springboot.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *登录页
 * @Author hxl
 * @Date  2019/4/28
 **/
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转至登录页
     * @return
     */
    @RequestMapping("/login")
    public String showLogin() {
        return "login/login";
    }

    //配置哪些角色可以访问哪些路径
    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }
}