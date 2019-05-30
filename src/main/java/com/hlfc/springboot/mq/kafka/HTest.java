package com.hlfc.springboot.mq.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * https://www.jianshu.com/p/04eff11430e4
 * @Auther: hxl
 * @Date: 2019/5/29 18:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTest {

    @Autowired
    private KafkaSender kafkaSender;


    @Test
    public void test(){

        for (int i = 0; i <10 ; i++) {

            kafkaSender.sendTest();
        }
    }
}
