package com.agrigov.service;

import com.agrigov.dto.NotificationRequest;
import com.agrigov.dto.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    NotificationResponse createNotification(NotificationRequest request);
    Page<NotificationResponse> getUserNotifications(Long userId, Pageable pageable);
    NotificationResponse markAsRead(Long notificationId);
}