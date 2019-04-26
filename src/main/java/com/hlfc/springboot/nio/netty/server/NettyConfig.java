package com.hlfc.springboot.nio.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ruanzz
 * @description 服务端全局配置类
 * @date 2019/4/9 上午 11:06
 **/
public class NettyConfig {

    /**
     * 存储每一个客户端接入进来时的channel对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 存放客户端IP对应的Channel Map
     */
    public static Map<String, Channel> channelMap = new HashMap<>();
}
