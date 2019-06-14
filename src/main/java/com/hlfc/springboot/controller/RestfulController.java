package com.hlfc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/rest")
public class RestfulController {


    @RequestMapping("/test")
    String hello() {
        return "Hello World!";
    }



    //post
    //application/json
    @RequestMapping(value = "/postjson",method = {RequestMethod.POST})
    public ResponseResult postjson(@RequestBody Map map){

        return  new ResponseResult(map);
    }
}