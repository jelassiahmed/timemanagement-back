package com.timemanagemenet.timemanagementapp.controller;

import com.timemanagemenet.timemanagementapp.entity.Notification;
import com.timemanagemenet.timemanagementapp.entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    WebSocketController websocketController;

    @GetMapping("/{idEmployee}")
    public List<Notification> getNotificationsByEmployeeId(@PathVariable Long idEmployee) {
        return notificationService.getNotificationsByEmployeeId(idEmployee);
    }


    @PostMapping("/open/{notificationId}")
    public ResponseEntity<String> markNotificationAsOpened(@PathVariable Long notificationId) {
        notificationService.markNotificationAsOpened(notificationId);
        websocketController.sendMessage(new WebSocketMessage("notification opened", notificationId));
        return ResponseEntity.ok("Notification marked as opened");
    }
}
