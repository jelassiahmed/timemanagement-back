package com.timemanagemenet.timemanagementapp.Controller;
import com.timemanagemenet.timemanagementapp.Entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.Service.WebSocket.WebSocketService;
import com.timemanagemenet.timemanagementapp.config.WebSocket.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("ws")
public class WebSocketController {

    @Autowired
    WebSocketService webSocketService;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(WebSocketMessage message) {
        System.out.println("Sending WebSocket message...");
        this.simpMessagingTemplate.convertAndSend("/topic/greetings", message);
        System.out.println("WebSocket message sent.");
    }
}