package com.hlfc.springboot.mq.mqtt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hxli
 * @since 2023/8/28/028
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTest {
    
    @Autowired
    MqttClientPush mqttClientPush;
    @Test
    public  void  test1(){
       String top = "eve/report/warning/99e755be0b72f49d471efb3ace6e90f5/fire/firePoint";
        mqttClientPush.publish(top,"123,123");
    }
}
