package com.sdp.logistics.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "message", nullable = false, length = 255)
    private String message;

    @Column(name = "notification_type", nullable = false, length = 50)
    private String notificationType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Notification() {
    }

    public Notification(Shipment shipment, Customer customer, String message, String notificationType) {
        this.shipment = shipment;
        this.customer = customer;
        this.message = message;
        this.notificationType = notificationType;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public Customer getCustomer() {
        return customer;
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

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}