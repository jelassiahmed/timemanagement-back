package com.timemanagemenet.timemanagementapp.service.webSocket;

import com.timemanagemenet.timemanagementapp.entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.config.webSocket.Greeting;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service("webSocketService")
public class WebSocketServiceImpl implements  WebSocketService{
    @Override
    public Greeting sendWebSocketMessage(WebSocketMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting(HtmlUtils.htmlEscape(message.getSocketMessage()));
    }
}