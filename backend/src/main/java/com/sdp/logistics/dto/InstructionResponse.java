package com.sdp.logistics.dto;

import java.time.LocalDateTime;

public class InstructionResponse {

    private Long instructionId;
    private Long shipmentId;
    private String trackingId;
    private Long customerId;
    private String instructionText;
    private LocalDateTime createdAt;
    private String message;

    public InstructionResponse(Long instructionId, Long shipmentId, String trackingId, Long customerId,
                               String instructionText, LocalDateTime createdAt, String message) {
        this.instructionId = instructionId;
        this.shipmentId = shipmentId;
        this.trackingId = trackingId;
        this.customerId = customerId;
        this.instructionText = instructionText;
        this.createdAt = createdAt;
        this.message = message;
    }

    public Long getInstructionId() {
        return instructionId;
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

    public String getInstructionText() {
        return instructionText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getMessage() {
        return message;
    }
}