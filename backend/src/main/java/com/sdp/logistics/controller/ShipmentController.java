package com.sdp.logistics.controller;

import com.sdp.logistics.dto.InstructionRequest;
import com.sdp.logistics.dto.InstructionResponse;
import com.sdp.logistics.dto.RescheduleRequest;
import com.sdp.logistics.dto.RescheduleResponse;
import com.sdp.logistics.dto.ShipmentResponse;
import com.sdp.logistics.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "http://localhost:5173")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ShipmentResponse>> getShipmentsByCustomer(@PathVariable Long customerId) {
        List<ShipmentResponse> response = shipmentService.getShipmentsByCustomer(customerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/track/{trackingId}")
    public ResponseEntity<ShipmentResponse> trackShipment(
            @PathVariable String trackingId,
            @RequestParam Long customerId
    ) {
        ShipmentResponse response = shipmentService.trackShipment(trackingId, customerId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{shipmentId}/reschedule")
    public ResponseEntity<RescheduleResponse> rescheduleShipment(
            @PathVariable Long shipmentId,
            @RequestParam Long customerId,
            @Valid @RequestBody RescheduleRequest request
    ) {
        RescheduleResponse response = shipmentService.rescheduleShipment(shipmentId, customerId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{shipmentId}/instructions")
    public ResponseEntity<InstructionResponse> updateDeliveryInstruction(
            @PathVariable Long shipmentId,
            @RequestParam Long customerId,
            @Valid @RequestBody InstructionRequest request
    ) {
        InstructionResponse response = shipmentService.updateDeliveryInstruction(shipmentId, customerId, request);
        return ResponseEntity.ok(response);
    }
}