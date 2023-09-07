/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hlfc.springboot.mq.mqtt;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@Slf4j
public class MqttInitConfig {

    /**
     * 服务器链接地址
     */
    @Value("${mqtt.url}")
    private String url;


    /**
     * 用户名
     */
    @Value("${mqtt.userName}")
    private String userName;

    /**
     * 密码
     */
    @Value("${mqtt.password}")
    private String password;

    /**
     * 客户端id
     */
    @Value("${mqtt.clientId}")
    private String clientId;

    /**
     * 超时时间
     */
    @Value("${mqtt.timeout}")
    private int timeout;

    /**
     * 保持连接数
     */
    @Value("${mqtt.keepalive}")
    private int keepalive;

    /**
     * 默认要订阅的主题
     */
    @Value("${mqtt.defaultTopic}")
    private String defaultTopic;

    /**
     * 订阅模式
     */
    @Value("${mqtt.pattern}")
    private String pattern;

    /**
     * 订阅分组
     */
    @Value("${mqtt.group}")
    private String group;

    /**
     * 客户端对象
     */
    @Autowired
    private MqttClientPush mqttClientPush;

    /**
     * 自定义主题
     */
    @Value("${mqtt.customTopic}")
    private String customTopic;

    /**
     * 初始化客户端对象
     * @return mqttPushClient
     */
    @Bean
    public MqttClientPush getMqttClientPush() {
        log.info(String.format("MQTT：配置参数值，url[%s], clientId[%s], userName[%s], password[%s]," +
                " timeout[%s], keepalive[%s], defaultTopic[%s]",
                url, clientId, userName, password, timeout, keepalive, defaultTopic));
        mqttClientPush.connect( url,  clientId, userName, password);
        return mqttClientPush;
    }

}
