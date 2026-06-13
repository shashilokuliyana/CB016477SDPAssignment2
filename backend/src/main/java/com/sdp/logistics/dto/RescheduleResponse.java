package com.sdp.logistics.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RescheduleResponse {

    private Long rescheduleId;
    private Long shipmentId;
    private String trackingId;
    private Long customerId;
    private LocalDate oldDeliveryDate;
    private LocalDate newDeliveryDate;
    private String status;
    private String reason;
    private LocalDateTime requestedAt;
    private String message;

    public RescheduleResponse(Long rescheduleId, Long shipmentId, String trackingId, Long customerId,
                              LocalDate oldDeliveryDate, LocalDate newDeliveryDate, String status,
                              String reason, LocalDateTime requestedAt, String message) {
        this.rescheduleId = rescheduleId;
        this.shipmentId = shipmentId;
        this.trackingId = trackingId;
        this.customerId = customerId;
        this.oldDeliveryDate = oldDeliveryDate;
        this.newDeliveryDate = newDeliveryDate;
        this.status = status;
        this.reason = reason;
        this.requestedAt = requestedAt;
        this.message = message;
    }

    public Long getRescheduleId() {
        return rescheduleId;
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

    public LocalDate getOldDeliveryDate() {
        return oldDeliveryDate;
    }

    public LocalDate getNewDeliveryDate() {
        return newDeliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public String getMessage() {
        return message;
    }
}