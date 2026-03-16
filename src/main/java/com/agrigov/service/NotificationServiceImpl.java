package com.agrigov.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrigov.dto.NotificationRequest;
import com.agrigov.dto.NotificationResponse;
import com.agrigov.enums.NotificationStatus;
import com.agrigov.model.Notification;
import com.agrigov.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setEntityId(request.getEntityId());
        notification.setMessage(request.getMessage());
        notification.setCategory(request.getCategory());
        
        notification.setCreatedDate(LocalDateTime.now());
        notification.setStatus(NotificationStatus.SENT);

        return mapToResponse(notificationRepository.save(notification));
    }

    @Override
    public Page<NotificationResponse> getUserNotifications(Long userId, Pageable pageable) {
        return notificationRepository.findByUserId(userId, pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional
    public NotificationResponse markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(NotificationStatus.READ);
        return mapToResponse(notificationRepository.save(notification));
    }

    // This helper method is crucial to fix the mapToResponse error
    private NotificationResponse mapToResponse(Notification entity) {
        NotificationResponse res = new NotificationResponse();
        res.setNotificationId(entity.getNotificationId());
        res.setUserId(entity.getUserId());
        res.setEntityId(entity.getEntityId());
        res.setMessage(entity.getMessage());
        res.setCategory(entity.getCategory());
        res.setStatus(entity.getStatus());
        res.setCreatedDate(entity.getCreatedDate());
        return res;
    }
}