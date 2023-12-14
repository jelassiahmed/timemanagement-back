package com.timemanagemenet.timemanagementapp.Service.Notification;

import com.timemanagemenet.timemanagementapp.Entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationsByEmployeeId(Long idEmployee);
    void createNotification(Long idEmployee, String message);

    void markNotificationAsOpened(Long notificationId);

}
