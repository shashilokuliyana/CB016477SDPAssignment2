package com.sdp.logistics.service;

import com.sdp.logistics.dto.NotificationResponse;
import com.sdp.logistics.entity.Notification;
import com.sdp.logistics.entity.Shipment;
import com.sdp.logistics.exception.ResourceNotFoundException;
import com.sdp.logistics.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationResponse> getNotificationsByCustomer(Long customerId) {
        List<Notification> notifications =
                notificationRepository.findByCustomerCustomerIdOrderByCreatedAtDesc(customerId);

        if (notifications.isEmpty()) {
            throw new ResourceNotFoundException("No notifications found for this customer");
        }

        return notifications.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void createShipmentNotification(Shipment shipment, String notificationType, String message) {
        Notification notification = new Notification(
                shipment,
                shipment.getCustomer(),
                message,
                notificationType
        );

        notificationRepository.save(notification);
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return new NotificationResponse(
                notification.getNotificationId(),
                notification.getShipment().getShipmentId(),
                notification.getShipment().getTrackingId(),
                notification.getCustomer().getCustomerId(),
                notification.getMessage(),
                notification.getNotificationType(),
                notification.getCreatedAt()
        );
    }
}