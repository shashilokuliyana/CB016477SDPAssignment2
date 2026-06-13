package com.sdp.logistics.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_reschedules")
public class DeliveryReschedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reschedule_id")
    private Long rescheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "old_delivery_date", nullable = false)
    private LocalDate oldDeliveryDate;

    @Column(name = "new_delivery_date", nullable = false)
    private LocalDate newDeliveryDate;

    @Column(name = "reason", length = 255)
    private String reason;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

    public DeliveryReschedule() {
    }

    public DeliveryReschedule(Shipment shipment, Customer customer, LocalDate oldDeliveryDate,
                              LocalDate newDeliveryDate, String reason) {
        this.shipment = shipment;
        this.customer = customer;
        this.oldDeliveryDate = oldDeliveryDate;
        this.newDeliveryDate = newDeliveryDate;
        this.reason = reason;
    }

    @PrePersist
    protected void onCreate() {
        this.requestedAt = LocalDateTime.now();
    }

    public Long getRescheduleId() {
        return rescheduleId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getOldDeliveryDate() {
        return oldDeliveryDate;
    }

    public LocalDate getNewDeliveryDate() {
        return newDeliveryDate;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRescheduleId(Long rescheduleId) {
        this.rescheduleId = rescheduleId;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOldDeliveryDate(LocalDate oldDeliveryDate) {
        this.oldDeliveryDate = oldDeliveryDate;
    }

    public void setNewDeliveryDate(LocalDate newDeliveryDate) {
        this.newDeliveryDate = newDeliveryDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}