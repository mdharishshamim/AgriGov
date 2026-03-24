package com.agrigov.dto;



import java.time.LocalDateTime;

import com.agrigov.enums.NotificationCategory;
import com.agrigov.enums.NotificationStatus;

import lombok.Data;
@Data
public class NotificationResponse {
    private Long notificationId;
    private Long userId;
    private Long entityId;
    private String message;
    private NotificationCategory category;
    private NotificationStatus status;
    private LocalDateTime createdDate;

    // Getters and Setters
	/*
	 * public Long getNotificationId() { return notificationId; } public void
	 * setNotificationId(Long notificationId) { this.notificationId =
	 * notificationId; }
	 * 
	 * public Long getUserId() { return userId; } public void setUserId(Long userId)
	 * { this.userId = userId; }
	 * 
	 * public Long getEntityId() { return entityId; } public void setEntityId(Long
	 * entityId) { this.entityId = entityId; }
	 * 
	 * public String getMessage() { return message; } public void setMessage(String
	 * message) { this.message = message; }
	 * 
	 * public NotificationCategory getCategory() { return category; } public void
	 * setCategory(NotificationCategory category) { this.category = category; }
	 * 
	 * public NotificationStatus getStatus() { return status; } public void
	 * setStatus(NotificationStatus status) { this.status = status; }
	 * 
	 * public LocalDateTime getCreatedDate() { return createdDate; } public void
	 * setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
	 */
}