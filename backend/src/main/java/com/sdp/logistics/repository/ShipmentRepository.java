package com.sdp.logistics.repository;

import com.sdp.logistics.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findByCustomerCustomerId(Long customerId);

    Optional<Shipment> findByTrackingIdAndCustomerCustomerId(String trackingId, Long customerId);

    Optional<Shipment> findByShipmentIdAndCustomerCustomerId(Long shipmentId, Long customerId);
}