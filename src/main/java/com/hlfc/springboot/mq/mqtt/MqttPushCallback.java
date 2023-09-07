package com.hlfc.springboot.mq.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mqtt回调类
 */
@Component
@Slf4j
public class MqttPushCallback implements MqttCallbackExtended {

    @Autowired
    private MqttClientPush mqttClientPush;

    @Autowired
    private MqttInitConfig mqttConfig;

    /**
     * 重连次数
     */
    private static int reConnectCount = 0;

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        long millis = 20000;
        if (reConnectCount > 30) {
            millis = 30000;
        } else if (reConnectCount > 10) {
            millis = 20000;
        }
        log.error(String.format("MQTT: 连接断开，%s秒后尝试重新建立连接", millis / 1000), cause);
        while (true) {
            try {
                Thread.sleep(millis);
                mqttClientPush.reconnect();
                break;
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
                continue;
            }

        }
    }



    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete---------" + token.isComplete());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        log.info("接收消息主题: " + topic);
        log.info("接收消息Qos: " + message.getQos());
        String curMsg = new String(message.getPayload());
        log.info("接收到的消息 {}",curMsg);
    }



    @Override
    public void connectComplete(boolean b, String s) {
        log.info(String.format("MQTT: 服务连接成功"));
        reConnectCount = 0;
        if (mqttConfig.getDefaultTopic() != null && !"".equals(mqttConfig.getDefaultTopic() ) ) {
            String[] topics = mqttConfig.getDefaultTopic().split(",");
            String pattern = mqttConfig.getPattern();
            String group = mqttConfig.getGroup();
            for (String topic: topics) {
                if (reConnectCount == 0) {
                    String subTopic = topic;
                    if (StringUtils.isNotBlank(pattern)) {
                        if ("queue".equals(pattern)) {
                            subTopic = "$queue/" + topic;
                        } else if ("share".equals(pattern)) {
                            // 分组共享订阅
                            subTopic = "$share/" + group + "/" + topic;
                        }
                    }
                    log.info("订阅主题：{}",subTopic);
                    mqttClientPush.subscribe(subTopic);
                }

            }
            String customTopic = mqttConfig.getCustomTopic();
            mqttClientPush.subscribe(customTopic);
            log.info("订阅主题：{}",customTopic);
        }
    }
}
