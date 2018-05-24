package com.yugioh.api.webapi.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @Author Create By lieber
 * @Description WebSocket配置
 * @Date Create in 2018/5/15 17:59
 * @Modify By
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        // 添加服务端点，可以理解为某一服务的唯一key值
        stompEndpointRegistry.addEndpoint("/socketApp");
        //当浏览器支持sock.js时执行该配置
        stompEndpointRegistry.addEndpoint("/socketApp").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 配置接受订阅消息地址前缀为topic的消息
        config.enableSimpleBroker("/topic");
        // Broker接收消息地址前缀
        config.setApplicationDestinationPrefixes("/app");
    }

}
