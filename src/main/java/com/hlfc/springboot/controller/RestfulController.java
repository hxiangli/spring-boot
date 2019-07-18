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


    //---------以下测试来自 com.hlfc.springboot.http.client.HTest

    //post
    //application/json
    @RequestMapping(value = "/postjson",method = {RequestMethod.POST})
    public ResponseResult postjson(@RequestBody Map map,String key){

        return  new ResponseResult(map);
    }


    //post
    //application/x-www-form-urlencoded
    @RequestMapping(value = "/postform",method = {RequestMethod.POST})
    public ResponseResult postform(String id,String key){

        return  new ResponseResult(id);
    }
}