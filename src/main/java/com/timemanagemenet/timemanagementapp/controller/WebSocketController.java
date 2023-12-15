package com.timemanagemenet.timemanagementapp.controller;
import com.timemanagemenet.timemanagementapp.entity.Notification;
import com.timemanagemenet.timemanagementapp.entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.service.notification.NotificationService;
import com.timemanagemenet.timemanagementapp.service.webSocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    WebSocketService webSocketService;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    NotificationService notificationService;

    public void sendMessage(WebSocketMessage message) {
        System.out.println("Sending WebSocket message...");
        this.simpMessagingTemplate.convertAndSend("/topic/greetings", message);
        System.out.println("WebSocket message sent.");
    }

    @MessageMapping("/send-notification/{idEmployee}")
    public void sendNotification(@DestinationVariable Long idEmployee, Notification notification) {
        notificationService.createNotification(idEmployee, notification.getMessage());
        System.out.println("Sending notification to employee with id: " + idEmployee);
        this.simpMessagingTemplate.convertAndSendToUser(idEmployee.toString(), "/topic/notification", notification);

    }
}