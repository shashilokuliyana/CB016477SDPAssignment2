package com.sdp.logistics.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "tracking_id", nullable = false, unique = true, length = 50)
    private String trackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "origin", nullable = false, length = 150)
    private String origin;

    @Column(name = "destination", nullable = false, length = 150)
    private String destination;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "expected_delivery_date", nullable = false)
    private LocalDate expectedDeliveryDate;

    @Column(name = "driver_name", length = 100)
    private String driverName;

    @Column(name = "special_instructions", length = 500)
    private String specialInstructions;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    public Shipment() {
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Customer getCustomer() {
        return customer;
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

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}