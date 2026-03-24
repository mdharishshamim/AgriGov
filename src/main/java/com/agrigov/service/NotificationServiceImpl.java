package com.agrigov.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.agrigov.dto.NotificationRequest;
import com.agrigov.dto.NotificationResponse;
import com.agrigov.enums.NotificationStatus;
import com.agrigov.model.Notification;
import com.agrigov.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Transactional
	public NotificationResponse createNotification(NotificationRequest request) {
		logger.info("Processing notification request for User ID: {} with Email: {}", request.getUserId(),
				request.getEmail());

		Notification notification = new Notification();
		notification.setUserId(request.getUserId());
		notification.setEntityId(request.getEntityId());
		notification.setMessage(request.getMessage());
		notification.setCategory(request.getCategory());
		notification.setCreatedDate(LocalDateTime.now());
		notification.setStatus(NotificationStatus.SENT);

		Notification savedNotification = notificationRepository.save(notification);
		logger.debug("Notification record persisted in database. ID: {}", savedNotification.getNotificationId());

		// Send email using the email provided in the request
		sendEmailNotification(request.getEmail(), request.getMessage());

		return mapToResponse(savedNotification);
	}
    @Async
	private void sendEmailNotification(String recipientEmail, String messageContent) {
		try {
			logger.info("Initiating email delivery to: {}", recipientEmail);

			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(recipientEmail);
			mail.setSubject("AgriGov: New Notification");
			mail.setText(messageContent);

			mailSender.send(mail);
			logger.info("Email successfully dispatched to {}", recipientEmail);
		} catch (Exception e) {
			// Log the error but don't crash the request
			logger.error("Critical failure during email dispatch to {}: {}", recipientEmail, e.getMessage());
		}
	}

	@Override
	public Page<NotificationResponse> getUserNotifications(Long userId, Pageable pageable) {
		logger.info("Retrieving paginated notifications for User ID: {}", userId);
		return notificationRepository.findByUserId(userId, pageable).map(this::mapToResponse);
	}

	@Override
	@Transactional
	public NotificationResponse markAsRead(Long notificationId) {
		logger.info("Attempting to update status to READ for Notification ID: {}", notificationId);
		return notificationRepository.findById(notificationId).map(n -> {
			n.setStatus(NotificationStatus.READ);
			Notification updated = notificationRepository.save(n);
			logger.info("Successfully updated status for Notification ID: {}", notificationId);
			return mapToResponse(updated);
		}).orElseThrow(() -> {
			logger.error("Update failed: Notification ID {} not found", notificationId);
			return new RuntimeException("Notification not found with ID: " + notificationId);
		});
	}

	@Override
	public Page<NotificationResponse> getAllNotifications(Pageable pageable) {
		logger.info("Admin Access: Fetching all notifications with parameters: {}", pageable);
		return notificationRepository.findAll(pageable).map(this::mapToResponse);
	}

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