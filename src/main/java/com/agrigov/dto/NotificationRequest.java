package com.agrigov.dto;

import com.agrigov.enums.NotificationCategory;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private Long entityId;

    @NotBlank(message = "Message content is required")
    private String message;

    @NotNull(message = "Category is required")
    private NotificationCategory category;

	/*
	 * // Getters and Setters public Long getUserId() { return userId; } public void
	 * setUserId(Long userId) { this.userId = userId; }
	 * 
	 * public String getEmail() { return email; } public void setEmail(String email)
	 * { this.email = email; }
	 * 
	 * public Long getEntityId() { return entityId; } public void setEntityId(Long
	 * entityId) { this.entityId = entityId; }
	 * 
	 * public String getMessage() { return message; } public void setMessage(String
	 * message) { this.message = message; }
	 * 
	 * public NotificationCategory getCategory() { return category; } public void
	 * setCategory(NotificationCategory category) { this.category = category; }
	 */
}