package com.hlfc.springboot.mq.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * mqtt客户端
 */
@Component
@Slf4j
public class MqttClientPush {

    @Autowired
    private MqttPushCallback pushCallback;


    private static MqttClient client;

    public static MqttClient getClient() {
        return client;
    }

    public static void setClient(MqttClient client) {
        MqttClientPush.client = client;
    }


    /**
     * 连接
     * @param host .
     * @param clientID .
     * @param username .
     * @param password .
     */
    public void connect(String host, String clientID, String username, String password) {
        MqttClient client;
        try {
            client = new MqttClient(host, clientID, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(20);
            options.setKeepAliveInterval(20);
            MqttClientPush.setClient(client);
            try {
                client.setCallback(pushCallback);
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description 重新连接
     * created 2021/9/10 9:16
     **/
    public void reconnect() {
        MqttClient client = MqttClientPush.getClient();
        if (!client.isConnected()) {
            try {
                client.reconnect();
            } catch (Exception e) {
                  log.error(String.format("MQTT: 客户端[%s]重新连接失败", client.getServerURI()), e);
            }
        }
    }

    /**
     * 发布，默认qos为0，非持久化
     *
     * @param topic       .
     * @param pushMessage .
     */
    public void publish(String topic, String pushMessage) {
        publish(0, false, topic, pushMessage);
    }

    /**
     * 发布主题和消息队列
     *
     * @param qos         .
     * @param retained    .
     * @param topic       .
     * @param pushMessage .
     */
    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = MqttClientPush.getClient().getTopic(topic);
        if (null == mTopic) {
          //  LOGGER.error("topic not exist");
        }

        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
           // token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic .
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * 订阅某个主题
     *
     * @param topic .
     * @param qos .
     */
    public void subscribe(String topic, int qos) {
        try {
            log.info("topic==="+topic);
            MqttClientPush.getClient().subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
