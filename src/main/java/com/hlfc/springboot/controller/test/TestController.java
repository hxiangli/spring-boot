package com.hlfc.springboot.controller.test;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: hxl
 * @Date: 2022/12/1 14:32
 */
@Controller
@RequestMapping("/api")
@Log
public class TestController {

    @RequestMapping("/test")
    public void test(){
        log.info("===测试001");
//        HighCpuTest.test();
        TestJvmOOM.test();
    }
}
