package com.timemanagemenet.timemanagementapp.service.notification;

import com.timemanagemenet.timemanagementapp.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationsByEmployeeId(Long idEmployee);
    void createNotification(Long idEmployee, String message);

    void markNotificationAsOpened(Long notificationId);

}
