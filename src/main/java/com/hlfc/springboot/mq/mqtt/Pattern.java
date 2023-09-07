package com.hlfc.springboot.mq.mqtt;

/**
 * description 主题订阅模式
 */
public enum Pattern {
    /**
     * 普通订阅
     */
    NONE,
    /**
     * 不带群组的共享订阅
     */
    QUEUE,
    /**
     * 带群组的共享订阅
     */
    SHARE;
}
