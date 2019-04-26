package com.hlfc.springboot.nio.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持  https://blog.csdn.net/moshowgame/article/details/80275084
 * @author hxl
 */
@Configuration  
public class WebSocketConfig {  
	
    @Bean  
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();  
    }  
  
}