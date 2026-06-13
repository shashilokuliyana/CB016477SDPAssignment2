package com.sdp.logistics.dto;

import java.time.LocalDateTime;

public class NotificationResponse {

    private Long notificationId;
    private Long shipmentId;
    private String trackingId;
    private Long customerId;
    private String message;
    private String notificationType;
    private LocalDateTime createdAt;

    public NotificationResponse(Long notificationId, Long shipmentId, String trackingId, Long customerId,
                                String message, String notificationType, LocalDateTime createdAt) {
        this.notificationId = notificationId;
        this.shipmentId = shipmentId;
        this.trackingId = trackingId;
        this.customerId = customerId;
        this.message = message;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getMessage() {
        return message;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}