package com.hlfc.springboot.nio.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.util.StringUtils;

/**
 * @author hxl
 * @description 服务端处理器
 * @date 2019/4/9 上午 10:57
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 客户端与服务端创建连接的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ChannelId channelId = channel.id();
        String remoteAddress = channel.remoteAddress().toString();
        System.out.println("客户端与服务端连接开始. channelId:{}, IP:{}"+ channelId.asShortText()+remoteAddress);
        NettyConfig.group.add(ctx.channel());
        NettyConfig.channelMap.put(getIP(remoteAddress), channel);
    }

    /**
     * 获取IP
     * @param remoteAddress
     * @return
     */
    private static String getIP(String remoteAddress){
        if(StringUtils.isEmpty(remoteAddress)){
            return "unknow";
        }
        String[] ipPorts =  remoteAddress.split(":");
        String ip = ipPorts[0];
        if(ip.startsWith("/")){
            return ip.split("/")[1];
        }
        return ip;
    }

    /**
     * 客户端与服务端断开连接时调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ChannelId channelId = channel.id();
        System.out.println("客户端与服务端连接关闭. channelId:{}"+channelId.asShortText());
        NettyConfig.group.remove(channel);
        String remoteAddress = channel.remoteAddress().toString();
        NettyConfig.channelMap.remove(getIP(remoteAddress));
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        ChannelId channelId = ctx.channel().id();
        System.out.println("信息接收完毕. channelId:{}"+ channelId.asShortText());
    }

    /**
     * 异常的时候调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ChannelId channelId = ctx.channel().id();
        System.out.println("Netty异常. channelId:{}"+channelId.asShortText()+cause);
        ctx.close();
    }

    /**
     * 处理客户端发来的信息
     * @param ctx
     * @param info
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object info) throws Exception {
        ChannelId channelId = ctx.channel().id();
        System.out.println("channelId:{}, 接收到客户端消息：{}"+ channelId.asShortText()+info.toString());
    }
}
