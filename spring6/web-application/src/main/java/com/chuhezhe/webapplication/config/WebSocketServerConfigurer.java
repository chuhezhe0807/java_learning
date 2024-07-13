package com.chuhezhe.webapplication.config;

import com.chuhezhe.webapplication.handler.MyStringWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * ClassName: WebSocketConfig
 * Package: com.chuhezhe.webapplication.config
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/13 20:04
 * @Version 1.0
 */
@Configuration
@EnableWebSocket // 开启websocket相关功能
public class WebSocketServerConfigurer implements WebSocketConfigurer {

    private final MyStringWebSocketHandler myStringWebSocketHandler;

    public WebSocketServerConfigurer(MyStringWebSocketHandler myStringWebSocketHandler) {
        this.myStringWebSocketHandler = myStringWebSocketHandler;
    }

    // 将创建好的 MyStringWebSocketHandler 注册到了 WebSocketHandlerRegistry
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 当客户端通过 "/connect" url和服务端连接通信时，使用 MyStringWebSocketHandler 处理会话
        // withSockJS的含义是，通信的客户端是通过SockJS实现的。
        registry.addHandler(myStringWebSocketHandler, "/connect").withSockJS();
    }
}
