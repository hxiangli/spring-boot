package com.hlfc.springboot.http.client;

import com.hlfc.springboot.db.mybatisplus.services.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * restTemplate 请求测试
 * @Auther: hxl
 * @Date: 2019/6/14 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTest {

    @Autowired
    private RestTemplate template;

    @Test
    public void test() {

       String url = "http://127.0.0.1:9091/rest/postjson?key=1";
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        //map
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("id", "20180416");

        //string
//        String json = "{\"id\":\"123\"}";

        //头部
        HttpHeaders headers = new HttpHeaders();
        //数据类型
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        //map
        HttpEntity<MultiValueMap> entity = new HttpEntity<MultiValueMap>(paramMap,headers);

        //string
//        HttpEntity<String> entity = new HttpEntity<String>(json,headers);

        // 1、使用postForObject请求接口
        String result = template.postForObject(url, entity, String.class);
        System.out.println("result1==================" + result);

        url = "http://127.0.0.1:9091/rest/postform?key=1";
        MediaType formtype = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(formtype);
        HttpEntity<MultiValueMap> entityform = new HttpEntity<MultiValueMap>(paramMap,headers);
        String resultform = template.postForObject(url, entityform, String.class);
        System.out.println("resultform1==================" + resultform);

//        // 2、使用postForEntity请求接口
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
//        ResponseEntity<String> response2 = template.postForEntity(url, httpEntity, String.class);
//        System.out.println("result2====================" + response2.getBody());
//
//        // 3、使用exchange请求接口
//        ResponseEntity<String> response3 = template.exchange(url, HttpMethod.POST, httpEntity, String.class);
//        System.out.println("result3====================" + response3.getBody());




//        restTemplate.e
    }



}
