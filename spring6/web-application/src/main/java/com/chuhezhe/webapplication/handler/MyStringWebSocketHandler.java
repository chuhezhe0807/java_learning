package com.chuhezhe.webapplication.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * ClassName: MyStringWebSocketHandler
 * Package: com.chuhezhe.webapplication.handler
 * Description: 本次处理的内容为文本，所以使用了 TextWebSocketHandler，如果是二进制数据，则需要使用 BinaryWebSocketHandler
 *
 * @Author Chuhezhe
 * @Create 2024/7/13 19:52
 * @Version 1.0
 */
@Component
public class MyStringWebSocketHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(MyStringWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("和客户端建立连接");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
        logger.info("连接异常", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        logger.info("和客户端断开连接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取到客户端发送过来的消息
        String receiveMessage = message.getPayload();
        logger.info(receiveMessage);
        // 发送消息给客户端
        session.sendMessage(new TextMessage(fakeAi(receiveMessage)));

        // 关闭连接
//        session.close(CloseStatus.NORMAL);
    }

    private static String fakeAi(String input) {
        if(input == null || "".equals(input)) {
            return "你说什么？没听清";
        }

        return input.replace("你", "我")
                .replace("吗", "")
                .replace("?", "!")
                .replace("? ", "! ");
    }
}
