package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Notification;
import com.timemanagemenet.timemanagementapp.Service.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/{idEmployee}")
    public List<Notification> getNotificationsByEmployeeId(@PathVariable Long idEmployee) {
        return notificationService.getNotificationsByEmployeeId(idEmployee);
    }
}
