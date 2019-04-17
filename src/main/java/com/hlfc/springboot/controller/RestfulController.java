package com.hlfc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {
    @RequestMapping("/rest")
    String hello() {
        return "Hello World!";
    }

}