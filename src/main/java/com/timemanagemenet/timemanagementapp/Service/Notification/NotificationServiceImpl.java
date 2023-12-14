package com.timemanagemenet.timemanagementapp.Service.Notification;

import com.timemanagemenet.timemanagementapp.Entity.Notification;
import com.timemanagemenet.timemanagementapp.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public List<Notification> getNotificationsByEmployeeId(Long idEmployee) {
        return notificationRepository.findByidEmployeeOrderByTimestampDesc(idEmployee);
    }

    @Override
    public void createNotification(Long idEmployee, String message) {
        Notification notification = new Notification();
        notification.setIdEmployee(idEmployee);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);

        messagingTemplate.convertAndSendToUser(idEmployee.toString(), "/topic/notification", notification);

    }
    @Override
    public void markNotificationAsOpened(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        // Mark the notification as opened
        notification.setOpened(true);
        notificationRepository.save(notification);
    }
}
