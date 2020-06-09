package com.hlfc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 表单提交测试
 * @Auther: hxl
 * @Date: 2020/4/15 18:52
 */
@Controller
@RequestMapping("/formsubmit")
public class FormsubmitController {


    @RequestMapping("")
    public void formsubmit(String username, String password, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("===username:,passowrd:"+username+password);
        //httpServletResponse.setStatus(401);//302 前台ajax 无法捕获，401可以
        httpServletResponse.sendError(401, "没有权限");
//        httpServletResponse.sendRedirect("/index");

    }
}
