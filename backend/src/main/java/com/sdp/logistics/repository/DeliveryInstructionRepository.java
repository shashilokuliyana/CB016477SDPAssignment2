package com.sdp.logistics.repository;

import com.sdp.logistics.entity.DeliveryInstruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryInstructionRepository extends JpaRepository<DeliveryInstruction, Long> {

    List<DeliveryInstruction> findByCustomerCustomerId(Long customerId);

    List<DeliveryInstruction> findByShipmentShipmentId(Long shipmentId);
}