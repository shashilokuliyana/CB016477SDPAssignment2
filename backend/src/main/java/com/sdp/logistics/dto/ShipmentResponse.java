package com.sdp.logistics.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShipmentResponse {

    private Long shipmentId;
    private String trackingId;
    private Long customerId;
    private String origin;
    private String destination;
    private String status;
    private LocalDate expectedDeliveryDate;
    private String driverName;
    private String specialInstructions;
    private LocalDateTime lastUpdated;

    public ShipmentResponse(
            Long shipmentId,
            String trackingId,
            Long customerId,
            String origin,
            String destination,
            String status,
            LocalDate expectedDeliveryDate,
            String driverName,
            String specialInstructions,
            LocalDateTime lastUpdated
    ) {
        this.shipmentId = shipmentId;
        this.trackingId = trackingId;
        this.customerId = customerId;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.driverName = driverName;
        this.specialInstructions = specialInstructions;
        this.lastUpdated = lastUpdated;
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

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}