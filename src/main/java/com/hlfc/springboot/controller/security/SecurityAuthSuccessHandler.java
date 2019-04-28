package com.hlfc.springboot.controller.security;

import com.alibaba.fastjson.JSON;
import com.hlfc.springboot.controller.security.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: hxl
 * @Date: 2019/4/28 16:20
 */
@Component
public class SecurityAuthSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

       SysUser user = (SysUser) authentication.getPrincipal();

        System.out.println("登录成功了！！"+user.getName());

        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            response.getWriter().write( JSON.toJSON(new SecurityAuthResult("200","登录成功")).toString());
        } catch (IOException e) {
        }
    }

}
