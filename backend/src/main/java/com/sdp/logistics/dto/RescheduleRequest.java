package com.sdp.logistics.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RescheduleRequest {

    @NotNull(message = "New delivery date is required")
    @FutureOrPresent(message = "New delivery date cannot be in the past")
    private LocalDate newDeliveryDate;

    @Size(max = 255, message = "Reason cannot exceed 255 characters")
    private String reason;

    public RescheduleRequest() {
    }

    public LocalDate getNewDeliveryDate() {
        return newDeliveryDate;
    }

    public void setNewDeliveryDate(LocalDate newDeliveryDate) {
        this.newDeliveryDate = newDeliveryDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}