package com.agrigov.dto;

import com.agrigov.enums.NotificationCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificationRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    private Long entityId;

    @NotBlank(message = "Message content is required")
    private String message;

    @NotNull(message = "Category is required")
    private NotificationCategory category;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public NotificationCategory getCategory() { return category; }
    public void setCategory(NotificationCategory category) { this.category = category; }
}