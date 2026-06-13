package com.sdp.logistics.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_instructions")
public class DeliveryInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instruction_id")
    private Long instructionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "instruction_text", nullable = false, length = 500)
    private String instructionText;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public DeliveryInstruction() {
    }

    public DeliveryInstruction(Shipment shipment, Customer customer, String instructionText) {
        this.shipment = shipment;
        this.customer = customer;
        this.instructionText = instructionText;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Long getInstructionId() {
        return instructionId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getInstructionText() {
        return instructionText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setInstructionId(Long instructionId) {
        this.instructionId = instructionId;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setInstructionText(String instructionText) {
        this.instructionText = instructionText;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}