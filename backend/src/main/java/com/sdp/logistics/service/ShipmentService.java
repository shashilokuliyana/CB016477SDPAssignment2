package com.sdp.logistics.service;

import com.sdp.logistics.dto.InstructionRequest;
import com.sdp.logistics.dto.InstructionResponse;
import com.sdp.logistics.dto.RescheduleRequest;
import com.sdp.logistics.dto.RescheduleResponse;
import com.sdp.logistics.dto.ShipmentResponse;
import com.sdp.logistics.entity.DeliveryInstruction;
import com.sdp.logistics.entity.DeliveryReschedule;
import com.sdp.logistics.entity.Shipment;
import com.sdp.logistics.exception.BusinessValidationException;
import com.sdp.logistics.exception.ResourceNotFoundException;
import com.sdp.logistics.repository.DeliveryInstructionRepository;
import com.sdp.logistics.repository.DeliveryRescheduleRepository;
import com.sdp.logistics.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final DeliveryRescheduleRepository deliveryRescheduleRepository;
    private final DeliveryInstructionRepository deliveryInstructionRepository;
    private final NotificationService notificationService;

    public ShipmentService(ShipmentRepository shipmentRepository,
                           DeliveryRescheduleRepository deliveryRescheduleRepository,
                           DeliveryInstructionRepository deliveryInstructionRepository,
                           NotificationService notificationService) {
        this.shipmentRepository = shipmentRepository;
        this.deliveryRescheduleRepository = deliveryRescheduleRepository;
        this.deliveryInstructionRepository = deliveryInstructionRepository;
        this.notificationService = notificationService;
    }

    public List<ShipmentResponse> getShipmentsByCustomer(Long customerId) {
        List<Shipment> shipments = shipmentRepository.findByCustomerCustomerId(customerId);

        if (shipments.isEmpty()) {
            throw new ResourceNotFoundException("No shipments found for this customer");
        }

        return shipments.stream()
                .map(this::mapToShipmentResponse)
                .toList();
    }

    public ShipmentResponse trackShipment(String trackingId, Long customerId) {
        Shipment shipment = shipmentRepository
                .findByTrackingIdAndCustomerCustomerId(trackingId.trim().toUpperCase(), customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for the provided tracking ID"));

        return mapToShipmentResponse(shipment);
    }

    public RescheduleResponse rescheduleShipment(Long shipmentId, Long customerId, RescheduleRequest request) {
        Shipment shipment = shipmentRepository
                .findByShipmentIdAndCustomerCustomerId(shipmentId, customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for this customer"));

        if ("DELIVERED".equalsIgnoreCase(shipment.getStatus())) {
            throw new BusinessValidationException("Delivered shipments cannot be rescheduled");
        }

        if (request.getNewDeliveryDate().isBefore(LocalDate.now())) {
            throw new BusinessValidationException("New delivery date cannot be in the past");
        }

        if (request.getNewDeliveryDate().equals(shipment.getExpectedDeliveryDate())) {
            throw new BusinessValidationException("New delivery date must be different from the current delivery date");
        }

        LocalDate oldDeliveryDate = shipment.getExpectedDeliveryDate();

        DeliveryReschedule deliveryReschedule = new DeliveryReschedule(
                shipment,
                shipment.getCustomer(),
                oldDeliveryDate,
                request.getNewDeliveryDate(),
                request.getReason()
        );

        DeliveryReschedule savedReschedule = deliveryRescheduleRepository.save(deliveryReschedule);

        shipment.setExpectedDeliveryDate(request.getNewDeliveryDate());
        shipment.setStatus("RESCHEDULED");
        shipment.setLastUpdated(LocalDateTime.now());
        shipmentRepository.save(shipment);

        notificationService.createShipmentNotification(
                shipment,
                "RESCHEDULE",
                "Your shipment " + shipment.getTrackingId() + " has been rescheduled to "
                        + shipment.getExpectedDeliveryDate() + "."
        );

        return new RescheduleResponse(
                savedReschedule.getRescheduleId(),
                shipment.getShipmentId(),
                shipment.getTrackingId(),
                shipment.getCustomer().getCustomerId(),
                oldDeliveryDate,
                shipment.getExpectedDeliveryDate(),
                shipment.getStatus(),
                savedReschedule.getReason(),
                savedReschedule.getRequestedAt(),
                "Delivery rescheduled successfully"
        );
    }

    public InstructionResponse updateDeliveryInstruction(Long shipmentId, Long customerId, InstructionRequest request) {
        Shipment shipment = shipmentRepository
                .findByShipmentIdAndCustomerCustomerId(shipmentId, customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for this customer"));

        if ("DELIVERED".equalsIgnoreCase(shipment.getStatus())) {
            throw new BusinessValidationException("Delivery instructions cannot be updated for delivered shipments");
        }

        String instructionText = request.getInstructionText().trim();

        DeliveryInstruction deliveryInstruction = new DeliveryInstruction(
                shipment,
                shipment.getCustomer(),
                instructionText
        );

        DeliveryInstruction savedInstruction = deliveryInstructionRepository.save(deliveryInstruction);

        shipment.setSpecialInstructions(instructionText);
        shipment.setLastUpdated(LocalDateTime.now());
        shipmentRepository.save(shipment);

        notificationService.createShipmentNotification(
                shipment,
                "INSTRUCTION",
                "Special delivery instructions were updated for shipment " + shipment.getTrackingId() + "."
        );

        return new InstructionResponse(
                savedInstruction.getInstructionId(),
                shipment.getShipmentId(),
                shipment.getTrackingId(),
                shipment.getCustomer().getCustomerId(),
                savedInstruction.getInstructionText(),
                savedInstruction.getCreatedAt(),
                "Delivery instruction saved successfully"
        );
    }

    private ShipmentResponse mapToShipmentResponse(Shipment shipment) {
        return new ShipmentResponse(
                shipment.getShipmentId(),
                shipment.getTrackingId(),
                shipment.getCustomer().getCustomerId(),
                shipment.getOrigin(),
                shipment.getDestination(),
                shipment.getStatus(),
                shipment.getExpectedDeliveryDate(),
                shipment.getDriverName(),
                shipment.getSpecialInstructions(),
                shipment.getLastUpdated()
        );
    }
}