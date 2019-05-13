package com.hlfc.springboot.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败
 * @Auther: hxl
 * @Date: 2019/4/28 16:23
 */
@Component
public class SecurityAuthFailedHandler  extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("登录失败了:"+exception.getMessage());
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            response.getWriter().write( JSON.toJSON(new SecurityAuthResult("999",exception.getMessage())).toString());
        } catch (IOException e) {
        }
    }
}