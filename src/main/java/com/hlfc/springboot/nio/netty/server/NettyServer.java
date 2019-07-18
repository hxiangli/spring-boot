package com.hlfc.springboot.nio.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

/**
 * @author hxl
 * @description 服务端
 * @date 2019/4/9 上午 11:18
 **/
@Component
public class NettyServer {

    private static final Logger LOG = LoggerFactory.getLogger(NettyServer.class);

    private Integer nettyPort = 9988;

    private String channelId;

    //自启动
    @PostConstruct
    public void init(){
        bind(nettyPort);
    }

    private void bind(int serverPort) {
        Thread thread = new Thread(() -> {
            //服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
            //连接处理group
            EventLoopGroup boss = new NioEventLoopGroup();
            //事件处理group
            EventLoopGroup worker = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 绑定处理group
            bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                    //保持连接数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //有数据立即发送
                    .option(ChannelOption.TCP_NODELAY, true)
                    //保持连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //处理新连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 增加任务处理
                            ChannelPipeline p = sc.pipeline();
                            p.addLast(
                                    // 使用了netty自带的编码器和解码器
                                    new StringDecoder(Charset.forName("UTF-8")),
                                    new StringEncoder(Charset.forName("UTF-8")),
                                    // 自定义的处理器
                                    new ServerHandler()
                            );
                        }
                    });

            //绑定端口，同步等待成功
            ChannelFuture future;
            try {
                future = bootstrap.bind(serverPort).sync();
                if (future.isSuccess()) {
                    LOG.info("服务端开启成功");
                } else {
                    LOG.error("服务端开启失败");
                }

                //等待服务监听端口关闭,就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //优雅地退出，释放线程池资源
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        });
        thread.start();
    }

    /**
     * 给指定的客户端发送消息
     * @param msg
     * @param system
     */
    public boolean sendMessage(String msg, String system) {
        if (StringUtils.isEmpty(NettyConfig.group)) {
            LOG.error("发送消息消息失败. 客户端都已经断开连接");
            return false;
        }
        LOG.info("发送消息:{}, sytem:{}", msg, system);
        Channel channel = NettyConfig.channelMap.get(channelId);
        channel.writeAndFlush(msg);
        return true;
    }
}
